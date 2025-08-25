package org.example;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.example.pages.CalendarPage;
import org.example.pages.LoginPage;
import org.example.pages.EventsListComponent;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FirstTest {

    Playwright playwright;
    Browser browser;
    Page page;

    LoginPage loginPage;
    CalendarPage calendarPage;
    EventsListComponent eventsListComponent;

    @BeforeMethod
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(100));
        page = browser.newPage(new Browser.NewPageOptions().setViewportSize(1920, 1080));

        loginPage = new LoginPage(page);
        calendarPage = new CalendarPage(page);
        eventsListComponent = new EventsListComponent(page);
    }

    @Test
    public void testCreateEvent() {
        System.out.println("--- Running Create Event Test ---");

        // 1. Вход
        loginPage.navigateToLoginUrl();
        loginPage.login("vip1337", "password");

        // 2. Нажатие на кнопку 'Создать событие'
        calendarPage.clickCreateEventButton();
        calendarPage.verifyNewEventPageIsVisible();

        // 3. Добавлено ожидание 3 секунды.
        page.waitForTimeout(3000);

        System.out.println("Create event test passed successfully!");
    }

    @Test
    public void testClickExistingEvent() {
        System.out.println("--- Running Click Existing Event Test ---");

        // 1. Вход
        loginPage.navigateToLoginUrl();
        loginPage.login("vip1337", "password");

        // 2. Нажатие на любое существующее событие
        eventsListComponent.clickFirstEvent();

        // 3. Добавлено ожидание 3 секунды после клика на событие
        page.waitForTimeout(3000);

        System.out.println("Click existing event test passed successfully!");
    }

    @AfterMethod
    public void teardown() {
        browser.close();
        playwright.close();
    }
}