package org.example.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import com.microsoft.playwright.Locator.ClickOptions;

// Основной Page Object для страницы календаря
public class CalendarPage {

    private final Page page;

    // Объявляем наш компонент как часть этой страницы
    private final EventsListComponent eventsList;

    public CalendarPage(Page page) {
        this.page = page;
        // Инициализируем компонент, передавая ему ссылку на страницу
        this.eventsList = new EventsListComponent(page);
    }

    // МЕТОДЫ ОСНОВНОЙ СТРАНИЦЫ

    // 1. Клик по кнопке "Создать событие"
    public void clickCreateEventButton() {
        System.out.println("Clicked 'Создать событие'.");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Создать событие")).click(new ClickOptions().setTimeout(5000));
    }

    // 2. Проверка перехода на страницу создания заявки (Планирование/Информация по заявке)
    public void verifyNewEventPageIsVisible() {
        assertThat(page.getByText("Планирование")).isVisible();
        System.out.println("Assertion successful: Successfully navigated to the 'Создать заявку' page.");
    }

    // МЕТОД ДЛЯ ДОСТУПА К КОМПОНЕНТУ

    // Важный метод: он возвращает объект нашего компонента,
    // что позволяет вызывать методы для работы с событиями из теста.
    public EventsListComponent eventsList() {
        return eventsList;
    }
}
