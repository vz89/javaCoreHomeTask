package main.java.com.vz89.hometask.view;

import main.java.com.vz89.hometask.controller.AccountController;
import main.java.com.vz89.hometask.model.AccountStatus;
import main.java.com.vz89.hometask.service.IOService;

public class AccountView {
    private AccountController accountController = new AccountController();

    public void run() {
        IOService.write("Введите одну из команд: ");
        IOService.write("getById - получить Account по Id ");
        IOService.write("findAll - получить список всех Account");
        IOService.write("create - создать новый Account");
        IOService.write("update - изменить cтатус существующего Account");
        IOService.write("delete - удалить существующий аккаунт Account");

        String command = "";

        while (!command.equals("exit")) {
            command = IOService.read();
            switch (command) {
                case ("getById"):
                    IOService.write("Введите Id: ");
                    IOService.write(accountController.getById(IOService.readLong()).toString());
                    break;
                case ("findAll"):
                    IOService.write(accountController.findAll().toString());
                    break;
                case ("create"):
                    IOService.write("Введите новый Account");
                    IOService.write(accountController.create(IOService.read()).toString() + " добавлена");
                    break;
                case ("update"):
                    IOService.write("Введите Id Account'a, который хотите изменить");
                    Long id = IOService.readLong();
                    IOService.write("Введите новый статус Account'a");
                    AccountStatus accountStatus = AccountStatus.valueOf(IOService.read());
                    IOService.write(accountController.update(id, accountStatus).toString());
                    break;
                case ("delete"):
                    IOService.write("Введите Id Skill'a, который хотите удалить");
                    id = IOService.readLong();
                    accountController.delete(id);
                    IOService.write("Удален элемент по Id " + id);
                    break;
            }
        }

    }
}
