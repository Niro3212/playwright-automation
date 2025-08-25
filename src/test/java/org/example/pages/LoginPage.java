package org.example.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

// Класс, который представляет "объект страницы" (Page Object) авторизации
public class LoginPage {

    // Объявляем переменную `page` типа Page
    // Она будет хранить ссылку на текущую страницу браузера, с которой мы работаем
    private final Page page;

    // Конструктор класса. Он принимает объект `page` при создании экземпляра.
    // Это позволяет этому классу взаимодействовать с правильной страницей.
    public LoginPage(Page page) {
        this.page = page;
    }

    // Публичный метод для навигации на URL страницы логина
    public void navigateToLoginUrl() {
        String URL = "https://h2ocleaningsystemtest.ru";
        page.navigate(URL);
        System.out.println("Page opened: " + URL);
    }

    // Публичный метод для выполнения авторизации
    // Он инкапсулирует (скрывает) всю логику:
    // 1. Находит поле логина и вводит текст
    // 2. Находит поле пароля и вводит текст
    // 3. Находит кнопку "Войти в систему" и кликает на нее
    public void login(String login, String password) {
        page.getByPlaceholder("Введите логин").fill(login);
        System.out.println("Login entered: " + login);

        page.getByPlaceholder("Введите пароль").fill(password);
        System.out.println("Password entered: " + password);

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Войти в систему")).click();
        System.out.println("Clicked 'Войти в систему'.");
    }
}
