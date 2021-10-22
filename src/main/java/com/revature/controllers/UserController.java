package com.revature.controllers;

import java.util.List;

import com.revature.models.UserClass;
import com.revature.services.UserClassSer;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class UserController implements Controller {

	private UserClassSer userSer = new UserClassSer();

	
	private Handler loginAttempt = (ctx) -> {
		UserClass uc = ctx.bodyAsClass(UserClass.class);	
		if(userSer.login(uc)) {
			
			ctx.req.getSession(); 
			System.out.println("Test in login if");
			ctx.status(200);
		}else {
			ctx.req.getSession().invalidate();// invalidates any open session tracking the client.
			System.out.println(uc);
			ctx.status(401);
		}
	};
	
	public Handler getAllUser = (ctx) -> {
		if (ctx.req.getSession(false) != null) {
			List<UserClass> list = userSer.getAllRec();
			ctx.json(list);
			ctx.status(200);
			
		} else {
			ctx.status(401);
		}
	};

	public Handler getRemById = (ctx) -> {
		if (ctx.req.getSession(false) != null) {
			try {
				String idString = ctx.pathParam("uc");
				int id = Integer.parseInt(idString);
				UserClass uc = userSer.getUserClass(id);
				ctx.json(uc);
				ctx.status(200);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				ctx.status(406);
			}
		} else {
			ctx.status(401);
		}
	};

	public Handler addUser = (ctx) -> {
		if (ctx.req.getSession(false) != null) {
			UserClass uc = ctx.bodyAsClass(UserClass.class);
			if (userSer.addUser(uc)) {
				ctx.status(201);
			} else {
				ctx.status(400);
			}
		} else {
			ctx.status(401);
		}
	};

	public Handler updateUser = (ctx) -> {
		if (ctx.req.getSession(false) != null) {
			UserClass uc = ctx.bodyAsClass(UserClass.class);
			if (userSer.updateUser(uc)) {
				ctx.status(200);
			} else {
				ctx.status(400);
			}
		} else {
			ctx.status(401);
		}
	};

	public Handler deleteUser = (ctx) -> {
		if (ctx.req.getSession(false) != null) {
			String id = ctx.pathParam("uc");
			try {
				int uc = Integer.parseInt(id);
				if (userSer.deleteUser(uc)) {
					ctx.status(200);
				} else {
					ctx.status(400);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				ctx.status(406);
			}
		} else {
			ctx.status(401);
		}
	};

	@Override
	public void addRoutes(Javalin app) {
		app.post("/login", this.loginAttempt);
		app.get("/uc", this.getAllUser);
		app.get("/uc/:uc", this.getRemById);
		app.post("/uc", this.addUser);
		app.put("/uc", this.updateUser);
		app.delete("/uc/:uc", this.deleteUser);
	}
}
