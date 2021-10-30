package com.revature;

import com.revature.controllers.Controller;
import com.revature.controllers.ReimController;
import com.revature.controllers.RoleController;
import com.revature.controllers.UserController;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class App {
	private static Javalin app;

	public static void main(String[] args) {
		app = Javalin.create((config) -> {
			config.addStaticFiles("/static", Location.CLASSPATH);
			
		});

		configure(new ReimController(), new RoleController(), new UserController());

		app.start(8080);

	}

	private static void configure(Controller... controllers) {
		for (Controller c : controllers) {
			c.addRoutes(app);
		}
	}

	
}
