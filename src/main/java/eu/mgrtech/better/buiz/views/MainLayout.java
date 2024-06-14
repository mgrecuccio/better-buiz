package eu.mgrtech.better.buiz.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;
import eu.mgrtech.better.buiz.views.clients.ClientsView;
import eu.mgrtech.better.buiz.views.employees.EmployeesView;
import eu.mgrtech.better.buiz.views.finance.FinanceView;
import eu.mgrtech.better.buiz.views.organization.OrganizationView;
import org.vaadin.lineawesome.LineAwesomeIcon;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H1 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H1();
        viewTitle.addClassNames("main-view-title", LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        Avatar avatar = new Avatar("User");
        MenuBar menuBar = new MenuBar();
        menuBar.addThemeVariants(MenuBarVariant.LUMO_TERTIARY_INLINE);

        MenuItem menuItem = menuBar.addItem(avatar);
        SubMenu subMenu = menuItem.getSubMenu();
        subMenu.addItem("Profile");
        subMenu.addItem("Settings");
        subMenu.addItem("Help");
        subMenu.addItem("Sign out");

        addToNavbar(true, toggle, viewTitle, menuBar);
    }

    private void addDrawerContent() {
        Span appName = new Span("BetterBuiz");
        appName.addClassNames(LumoUtility.FontWeight.SEMIBOLD, LumoUtility.FontSize.LARGE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();
        nav.addItem(
                new SideNavItem("Organization", OrganizationView.class, LineAwesomeIcon.PENCIL_RULER_SOLID.create()));
        nav.addItem(new SideNavItem("Clients", ClientsView.class, LineAwesomeIcon.BUSINESS_TIME_SOLID.create()));
        nav.addItem(new SideNavItem("Employees", EmployeesView.class, LineAwesomeIcon.PEOPLE_CARRY_SOLID.create()));
        nav.addItem(new SideNavItem("Finance", FinanceView.class, LineAwesomeIcon.EURO_SIGN_SOLID.create()));

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();
        layout.addClassNames("layout", LumoUtility.Gap.MEDIUM);

        Paragraph content = new Paragraph();
        content.addClassName("footer-content");
        content.setText("MGRTECH all rights reserved");
        layout.add(content);
        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
