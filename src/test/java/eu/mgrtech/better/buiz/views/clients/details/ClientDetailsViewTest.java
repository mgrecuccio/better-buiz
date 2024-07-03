package eu.mgrtech.better.buiz.views.clients.details;

import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabSheet;
import eu.mgrtech.better.buiz.services.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientDetailsViewTest {

    private ClientDetailsView clientDetailsView;
    private ProjectService projectService;

    @BeforeEach
    public void setUp() {
        projectService = new ProjectService();
        clientDetailsView = new ClientDetailsView(projectService);
    }

    @Test
    public void theStructureIsCorrectTest() {
        TabSheet tabSheet = clientDetailsView.tabSheet;
        Tab contractTab = tabSheet.getTabAt(0);
        Tab projectTab = tabSheet.getTabAt(1);

        assertNotNull(contractTab);
        assertNotNull(projectTab);

        assertTrue(contractTab.isSelected());
        assertFalse(projectTab.isSelected());

        assertEquals("Contracts", contractTab.getLabel());
        assertEquals("Projects", projectTab.getLabel());
    }

    @Test
    public void whenSetParameterIsCalledWithAValidClientIdNoExceptionIsThrownTest() {
        clientDetailsView.setParameter(null, "test-client-id");
    }

    @Test
    public void whenSetParameterIsCalledWithAnInvalidClientIdExceptionIsThrownTest() {
        Exception exception = assertThrows(RuntimeException.class, () -> clientDetailsView.setParameter(null, null));

        String expectedMessage = "Invalid client ID";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}