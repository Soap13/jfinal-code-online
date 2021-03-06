package com.jfinal.ext.codeonline.webdesigner.entity;

import com.jfinal.core.Controller;
import com.jfinal.ext.codeonline.webdesigner.project.Project;
import com.jfinal.ext.render.DwzRender;

public class EntityController extends Controller {
    public void index() throws Exception {
        Integer projectId = getParaToInt("project.id");
        keepModel(Project.class);
        setAttr("page", Entity.DAO.page(projectId, getParaToInt(0, 1), 5));
    }

    public void save() {
        Entity entity = getModel(Entity.class);
        if (entity.getInt("id") == null) {
            entity.save();
            entity.createPrimaryKey();
        } else {
            entity.update();
        }
        render(DwzRender.closeCurrentAndRefresh("entity"));
    }

    public void edit() {
        Project project = getModel(Project.class);
        Entity entity = Entity.DAO.findById(getPara(0));
        if (entity == null) {
            entity = new Entity();
            entity.set("project_id", project.getInt("id"));
        }
        setAttr("entity", entity);
    }

    public void delete() {
        Entity.DAO.deleteById(getPara(0));
        render(DwzRender.success());
    }
}
