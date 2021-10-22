package com.revature.controllers;

import java.util.List;

import com.revature.models.UserRoles;
import com.revature.models.UserRoles;
import com.revature.services.UserRolesSer;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class RoleController implements Controller {

	private UserRolesSer uRolSer = new UserRolesSer();

	public Handler getAllRole = (ctx) -> {
		if (ctx.req.getSession(false) != null) {
			List<UserRoles> list = uRolSer.getAllRec();
			ctx.json(list);
			ctx.status(200);
		} else {
			ctx.status(401);
		}
	};

	public Handler getRemById = (ctx) -> {
		if (ctx.req.getSession(false) != null) {
			try {
				String idString = ctx.pathParam("urole");
				int id = Integer.parseInt(idString);
				UserRoles urole = uRolSer.getUserRoles(id);
				ctx.json(urole);
				ctx.status(200);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				ctx.status(406);
			}
		} else {
			ctx.status(401);
		}
	};

	public Handler addRole = (ctx) -> {
		if (ctx.req.getSession(false) != null) {
			UserRoles urole = ctx.bodyAsClass(UserRoles.class);
			if (uRolSer.addRole(urole)) {
				ctx.status(201);
			} else {
				ctx.status(400);
			}
		} else {
			ctx.status(401);
		}
	};

	public Handler updateRole = (ctx) -> {
		if (ctx.req.getSession(false) != null) {
			UserRoles urole = ctx.bodyAsClass(UserRoles.class);
			if (uRolSer.updateRole(urole)) {
				ctx.status(200);
			} else {
				ctx.status(400);
			}
		} else {
			ctx.status(401);
		}
	};

	public Handler deleteRole = (ctx) -> {
		if (ctx.req.getSession(false) != null) {
			String id = ctx.pathParam("urole");
			try {
				int urole = Integer.parseInt(id);
				if (uRolSer.deleteRole(urole)) {
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
		app.get("/urole", this.getAllRole);
		app.get("/urole/:urole", this.getRemById);
		app.post("/urole", this.addRole);
		app.put("/urole", this.updateRole);
		app.delete("/urole/:urole", this.deleteRole);
	}
}
