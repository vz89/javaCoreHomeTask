package com.vz89.hometask.view;

import com.vz89.hometask.controller.SkillController;
import com.vz89.hometask.service.IOService;

public class SkillView {
    private SkillController skillController = new SkillController();
    private RunnerView runnerView = new RunnerView();

    public void run() {
        IOService.write("Введите одну из команд: ");
        IOService.write("getById - получить Skill по Id ");
        IOService.write("findAll - получить список всех Skill");
        IOService.write("create - создать новый Skill");
        IOService.write("update - изменить существующий скилл Skill");
        IOService.write("delete - удалить существующий скилл Skill");

        String command = "";

        while (!command.equals("exit")) {
            command = IOService.read();
            switch (command) {
                case ("getById"):
                    IOService.write("Введите Id: ");
                    IOService.write(skillController.getById(IOService.readLong()).toString());
                    break;
                case ("findAll"):
                    IOService.write(skillController.findAll().toString());
                    break;
                case ("create"):
                    IOService.write("Введите новый Skill");
                    IOService.write(skillController.create(IOService.read()).toString() + " добавлена");
                    break;
                case ("update"):
                    IOService.write("Введите Id Skill'a, который хотите изменить");
                    Long id = IOService.readLong();
                    IOService.write("Введите новое наименование Skill'a");
                    String newName = IOService.read();
                    IOService.write(skillController.update(id, newName).toString());
                    break;
                case ("delete"):
                    IOService.write("Введите Id Skill'a, который хотите удалить");
                    Long skillIdForDelete = IOService.readLong();
                    skillController.delete(skillIdForDelete);
                    IOService.write("Удален элемент по Id " + skillIdForDelete);
                    break;
            }
        }
        runnerView.run();
    }
}
