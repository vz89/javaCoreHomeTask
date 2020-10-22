package com.vz89.hometask.view;


import com.vz89.hometask.controller.AccountController;
import com.vz89.hometask.controller.DeveloperController;
import com.vz89.hometask.controller.SkillController;
import com.vz89.hometask.service.IOService;

public class DeveloperView {
    private DeveloperController developerController = new DeveloperController();
    private AccountController accountController = new AccountController();
    private SkillController skillController = new SkillController();

    public void run() {
        IOService.write("Введите одну из команд: ");
        IOService.write("getById - получить Developer по Id ");
        IOService.write("findAll - получить список всех Developers");
        IOService.write("create - создать нового Developer");
        IOService.write("update - изменить имя и фамилию существующего Developer");
        IOService.write("updateSkills - изменить навыки Developer");
        IOService.write("delete - удалить существующего Developer");

        String command = "";

        while (!command.equals("exit")) {
            command = IOService.read();
            switch (command) {
                case ("getById"):
                    IOService.write("Введите Id: ");
                    IOService.write(developerController.getById(IOService.readLong()).toString());
                    break;
                case ("findAll"):
                    IOService.write(developerController.findAll().toString());
                    break;
                case ("create"):
                    IOService.write("Введите имя");
                    String firstName = IOService.read();
                    IOService.write("Введите фамилию");
                    String lastName = IOService.read();
                    IOService.write("Выберите ID аккаунта, к которому будет привязан данный Developer");
                    IOService.write(accountController.findAll().toString());
                    Long accountId = IOService.readLong();
                    IOService.write("Выберите ID скиллов через запятую");
                    IOService.write(skillController.findAll().toString());
                    String skillString = IOService.read();
                    IOService.write(developerController.create(firstName, lastName, accountId, skillString).toString() + " добавлен");
                    break;
                case ("update"):
                    IOService.write("Введите Id Developer'a, который хотите изменить");
                    Long id = IOService.readLong();
                    IOService.write("Введите имя");
                    firstName = IOService.read();
                    IOService.write("Введите фамилию");
                    lastName = IOService.read();
                    IOService.write(developerController.update(id, firstName, lastName).toString());
                    break;
                case ("updateSkills"):
                    IOService.write("Введите Id Developer'a, который хотите изменить");
                    id = IOService.readLong();
                    IOService.write("Выберите ID скиллов через запятую");
                    IOService.write(skillController.findAll().toString());
                    skillString = IOService.read();
                    IOService.write(developerController.updateSkill(id, skillString).toString());
                    break;
                case ("delete"):
                    IOService.write("Введите Id Developer'a, который хотите удалить");
                    id = IOService.readLong();
                    developerController.delete(id);
                    IOService.write("Удален элемент по Id " + id);
                    break;
            }
        }
    }
}
