package org.example.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Locator.ClickOptions;

public class EventsListComponent {

    private final Page page;

    public EventsListComponent(Page page) {
        this.page = page;
    }

    // ИСПРАВЛЕННЫЙ ЛОКАТОР: ищем кликабельную ссылку <a>
    private Locator eventItems() {
        return page.locator("a.customEvent.cleaning");
    }

    // Этот метод не нуждается в waitForEvents(), так как .click() делает это автоматически
    public void clickFirstEvent() {
        System.out.println("Clicking the first visible event.");
        eventItems().first().click(new ClickOptions().setTimeout(5000));
        System.out.println("Clicked on the first event.");
    }

    // САМЫЙ НАДЕЖНЫЙ МЕТОД: нажать по полному тексту события
    public void clickEventByText(String eventName) {
        System.out.println("Waiting for event '" + eventName + "' to be clickable and clicking it.");
        // Используем CSS-селектор :has-text() для поиска кликабельного элемента с точным текстом
        page.locator("a.customEvent.cleaning:has-text(\"" + eventName + "\")").click(new ClickOptions().setTimeout(5000));
        System.out.println("Clicked event: " + eventName);
    }

    public boolean hasEvents() {
        return eventItems().count() > 0;
    }
}