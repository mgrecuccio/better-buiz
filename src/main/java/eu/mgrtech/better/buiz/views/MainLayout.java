package eu.mgrtech.better.buiz.views;

import org.vaadin.lineawesome.LineAwesomeIcon;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;

import eu.mgrtech.better.buiz.services.SecurityService;
import eu.mgrtech.better.buiz.views.clients.ClientsView;
import eu.mgrtech.better.buiz.views.finance.FinanceView;
import eu.mgrtech.better.buiz.views.invoicing.InvoicingToolView;
import eu.mgrtech.better.buiz.views.organization.OrganizationView;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private static final String FOOTER_CONTENT = "Powered by MGRTECH© 2024";
    private static final String ORGANIZATION_LABEL = "Organization";
    private static final String CLIENTS_LABEL = "Clients";
    private static final String INVOICING_TOOL_LABEL = "Invoicing Tool";
    private static final String FINANCE_LABEL = "Finance";
    private static final String APP_NAME = "BetterBuiz";
    private static final String PROFILE = "Profile";
    private static final String SETTINGS = "Settings";
    private static final String HELP = "Help";
    private static final String SIGN_OUT ="Sign out";
    private static final String USER_AVATAR = "UserAvatar";

    private final SecurityService securityService;

    private Span profileItem = new Span(PROFILE);
    private Span settingsItem = new Span(SETTINGS);
    private Span helpItem = new Span(HELP);
    private Span logoutItem = new Span(SIGN_OUT);

    H1 viewTitle;
    Avatar avatar = new Avatar(USER_AVATAR);
    MenuBar menuBar = new MenuBar();
    Paragraph footerContent = new Paragraph();
    SideNav nav;

    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;

        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addDrawerContent() {
        var appName = new Span(APP_NAME);
        appName.addClassNames(LumoUtility.FontWeight.SEMIBOLD, LumoUtility.FontSize.LARGE);

        var header = new Header(appName);
        var scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private Footer createFooter() {
        var layout = new Footer();
        layout.addClassNames("layout", LumoUtility.Gap.MEDIUM);

        footerContent.addClassName("footer-content");
        footerContent.setText(FOOTER_CONTENT);

        layout.add(footerContent);
        return layout;
    }

    private SideNav createNavigation() {
        nav = new SideNav();

        nav.addItem(new SideNavItem(ORGANIZATION_LABEL, OrganizationView.class, LineAwesomeIcon.PENCIL_RULER_SOLID.create()));
        nav.addItem(new SideNavItem(CLIENTS_LABEL, ClientsView.class, LineAwesomeIcon.BUSINESS_TIME_SOLID.create()));
        nav.addItem(new SideNavItem(INVOICING_TOOL_LABEL, InvoicingToolView.class, LineAwesomeIcon.TOOLS_SOLID.create()));
        nav.addItem(new SideNavItem(FINANCE_LABEL, FinanceView.class, LineAwesomeIcon.EURO_SIGN_SOLID.create()));

        return nav;
    }

    private void addHeaderContent() {
        var toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H1();
        viewTitle.addClassNames("main-view-title", LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        menuBar.addThemeVariants(MenuBarVariant.LUMO_TERTIARY_INLINE);
        logoutItem.addClickListener(e -> securityService.logout());

        var menuItem = menuBar.addItem(avatar);
        menuItem.addClassName("menu-item");

        configureSubmenu(menuItem.getSubMenu());
        addToNavbar(true, toggle, viewTitle, menuBar);
    }

    private void configureSubmenu(SubMenu subMenu) {
        subMenu.addItem(profileItem);
        subMenu.addItem(settingsItem);
        subMenu.addItem(helpItem);
        subMenu.addItem(logoutItem);
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
