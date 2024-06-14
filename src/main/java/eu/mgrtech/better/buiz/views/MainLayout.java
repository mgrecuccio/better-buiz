package eu.mgrtech.better.buiz.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
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

    private static final String FOOTER_CONTENT = "Powered by MGRTECH© 2024";
    private static final String ORGANIZATION_LABEL = "Organization";
    private static final String CLIENTS_LABEL = "Clients";
    private static final String EMPLOYEES_LABEL = "Employees";
    private static final String FINANCE_LABEL = "Finance";
    private static final String APP_NAME = "BetterBuiz";
    private static final String PROFILE = "Profile";
    private static final String SETTINGS = "Settings";
    private static final String HELP = "Help";
    private static final String SIGN_OUT = "Sign out";
    private static final String USER_AVATAR = "UserAvatar";

    H1 viewTitle;
    Avatar avatar = new Avatar(USER_AVATAR);
    MenuBar menuBar = new MenuBar();
    Paragraph footerContent = new Paragraph();
    SideNav nav;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        var toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H1();
        viewTitle.addClassNames("main-view-title", LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        menuBar.addThemeVariants(MenuBarVariant.LUMO_TERTIARY_INLINE);

        var menuItem = menuBar.addItem(avatar);
        var subMenu = menuItem.getSubMenu();
        subMenu.addItem(PROFILE);
        subMenu.addItem(SETTINGS);
        subMenu.addItem(HELP);
        subMenu.addItem(SIGN_OUT);

        addToNavbar(true, toggle, viewTitle, menuBar);
    }

    private void addDrawerContent() {
        var appName = new Span(APP_NAME);
        appName.addClassNames(LumoUtility.FontWeight.SEMIBOLD, LumoUtility.FontSize.LARGE);
        var header = new Header(appName);

        var scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private SideNav createNavigation() {
        nav = new SideNav();
        nav.addItem(new SideNavItem(ORGANIZATION_LABEL, OrganizationView.class, LineAwesomeIcon.PENCIL_RULER_SOLID.create()));
        nav.addItem(new SideNavItem(CLIENTS_LABEL, ClientsView.class, LineAwesomeIcon.BUSINESS_TIME_SOLID.create()));
        nav.addItem(new SideNavItem(EMPLOYEES_LABEL, EmployeesView.class, LineAwesomeIcon.PEOPLE_CARRY_SOLID.create()));
        nav.addItem(new SideNavItem(FINANCE_LABEL, FinanceView.class, LineAwesomeIcon.EURO_SIGN_SOLID.create()));
        return nav;
    }

    private Footer createFooter() {
        var layout = new Footer();
        layout.addClassNames("layout", LumoUtility.Gap.MEDIUM);

        footerContent.addClassName("footer-content");
        footerContent.setText(FOOTER_CONTENT);

        layout.add(footerContent);
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
