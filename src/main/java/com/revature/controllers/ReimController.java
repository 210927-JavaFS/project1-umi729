package com.revature.controllers;

import java.util.List;

import com.revature.models.Reimbursment;
import com.revature.models.Rstatus;
import com.revature.services.ReimbursmentSer;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ReimController implements Controller {

	private ReimbursmentSer remSer = new ReimbursmentSer();

	public Handler getAllReim = (ctx) -> {
		if (ctx.req.getSession(false) != null) {
			List<Reimbursment> list = remSer.getAllRec();
			ctx.json(list);
			ctx.status(200);
		} else {
			ctx.status(401);
		}
	};

	

	public Handler getRemByStatus = (ctx) -> {
		if (ctx.req.getSession(false) != null) {
			try {
				String idString = ctx.pathParam("astatus");
				System.out.println(idString);
				List<Reimbursment> lst = remSer.getReimByStatus(idString);
				ctx.json(lst);
				ctx.status(200);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				ctx.status(406);
			}
		} else {
			ctx.status(401);
		}
	};

	
	public Handler addReim = (ctx) -> {
		if (ctx.req.getSession(false) != null) {
			Reimbursment reim = ctx.bodyAsClass(Reimbursment.class);
			if (remSer.addReim(reim)) {
				ctx.status(201);
			} else {
				ctx.status(400);
			}
		} else {
			ctx.status(401);
		}
	};

	public Handler updateStatus = (ctx) -> {
		if (ctx.req.getSession(false) != null) {
			Rstatus status = ctx.bodyAsClass(Rstatus.class);
			if (remSer.updateRem(status.getId(), status.getStatus())) {
				ctx.status(204);
			} else {
				ctx.status(400);
			}
		} else {
			ctx.status(401);
		}
	};

	public Handler deleteReim = (ctx) -> {
		if (ctx.req.getSession(false) != null) {
			String id = ctx.pathParam("reim");
			try {
				int reim = Integer.parseInt(id);
				if (remSer.deleteReim(reim)) {
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
	public Handler getRemById = (ctx) -> {
		if (ctx.req.getSession(false) != null) {
			try {
				String idString = ctx.pathParam("reim");
				int id = Integer.parseInt(idString);
				Reimbursment reim = remSer.getReimById(id);
				ctx.json(reim);
				ctx.status(200);
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
		app.get("/reim", this.getAllReim);
		app.get("/reim/:reim", this.getRemById);
		app.get("/status/:astatus", this.getRemByStatus);
		app.post("/reim", this.addReim);
		app.put("/reim", this.updateStatus);
		app.delete("/reim/:reim", this.deleteReim);
	}
}
