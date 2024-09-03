package eu.mgrtech.better.buiz.views.clients.details;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.mgrtech.better.buiz.services.ProjectService;
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
        var tabSheet = clientDetailsView.tabSheet;
        var contractTab = tabSheet.getTabAt(0);
        var invoiceTab = tabSheet.getTabAt(1);
        var projectTab = tabSheet.getTabAt(2);

        assertNotNull(contractTab);
        assertNotNull(invoiceTab);
        assertNotNull(projectTab);

        assertTrue(contractTab.isSelected());
        assertFalse(invoiceTab.isSelected());
        assertFalse(projectTab.isSelected());

        assertEquals("Contracts", contractTab.getLabel());
        assertEquals("Invoices", invoiceTab.getLabel());
        assertEquals("Projects", projectTab.getLabel());
    }

    @Test
    public void whenSetParameterIsCalledWithAValidClientIdNoExceptionIsThrownTest() {
        clientDetailsView.setParameter(null, "test-client-id");
    }

    @Test
    public void whenSetParameterIsCalledWithAnInvalidClientIdExceptionIsThrownTest() {
        var exception = assertThrows(RuntimeException.class, () -> clientDetailsView.setParameter(null, null));

        var expectedMessage = "Invalid client ID";
        var actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}