package com.cuantisoft.backtransaction.controller;

//  pruebas de integración en un entorno controlado utilizando mocking

import com.cuantisoft.backtransaction.AbstractContextTest;
import com.cuantisoft.backtransaction.model.dto.TransactionRequestWebDto;
import com.cuantisoft.backtransaction.model.entity.Transaction;
import com.cuantisoft.backtransaction.repository.TransactionRepository;
import com.cuantisoft.backtransaction.service.TransactionService;
import com.cuantisoft.backtransaction.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//  integración con mocking
@WebMvcTest(controllers = {TransactionController.class})
@ContextConfiguration(classes = {TransactionControllerTest.TestConfig.class})
public class TransactionControllerTest extends AbstractContextTest {

    private final static String BASE_PATH = "/api/v1/transaction";

    //@Autowired: Inyecta automáticamente una instancia de MockMvc en la prueba
    @Autowired
    private MockMvc mockMvc; // Permite realizar peticiones HTTP de manera controlada para probar la interacción con el controlador sin necesidad de un servidor real

    //@MockBean: Esta anotación se usa para crear un objeto simulado (mock) de TransactionRepository
    @MockBean
    private TransactionRepository transactionRepository;

    // Define beans que se usarán en la prueba.
    // Carga el conexto de TestConfig no el contexto completo
    public static class TestConfig {

        @Bean
        public TransactionService transactionService(TransactionRepository transactionRepository) {
            return new TransactionServiceImpl(transactionRepository);
        }

        @Bean
        public TransactionController transactionController(TransactionService transactionService) {
            return new TransactionController(transactionService);
        }
    }

    @Test
    void shouldCreateTransactionSuccessfully() throws Exception {

        TransactionRequestWebDto requestInput = convertTo("/transaction/1-request.json", TransactionRequestWebDto.class);
        Transaction response = convertTo("/transaction/1-response.json", Transaction.class);

        Mockito.when(transactionRepository.save(ArgumentMatchers.any())).thenReturn(response);

        mockMvc.perform(post(BASE_PATH)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(convertToJSONString(requestInput))
                .header("Authorization", DEFAULT_TOKEN)
        ).andExpect(status().isOk());
    }
}
