import com.orness.hexacustomer.core.model.Customer;
import com.orness.hexacustomer.core.adapter.CustomerAdapter;
import com.orness.hexacustomer.core.exception.CustomerNotFoundException;
import com.orness.hexacustomer.core.port.persistance.AgifyPort;
import com.orness.hexacustomer.core.port.persistance.CustomerPersistencePort;
import com.orness.hexacustomer.core.port.primary.CustomerPort;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CustomerAdapterTest {
    private static final CustomerPersistencePort CUSTOMER_REPOSITORY_PERSISTENCE = mock(CustomerPersistencePort.class);
    private static final AgifyPort AGIFY_PORT = mock(AgifyPort.class);
    private static final Validator VALIDATOR = mock(Validator.class);
    private static final CustomerPort tested = new CustomerAdapter(CUSTOMER_REPOSITORY_PERSISTENCE, AGIFY_PORT, VALIDATOR);
    @Test
    void shouldSaveCustomer(){
        Customer customer = new Customer(UUID.randomUUID(), "Lennon", "Lennon","bob.lennon@gmail.com");
        final UUID id = tested.createCustomer(customer);

        verify(CUSTOMER_REPOSITORY_PERSISTENCE).save(any(Customer.class));
        assertNotNull(id);
    }

    @Test
    void shouldReturnCustomerWhenFound(){

        Customer customer = new Customer(UUID.randomUUID(), "Lennon", "Lennon","bob.lennon@gmail.com");
        when(CUSTOMER_REPOSITORY_PERSISTENCE.findByMail(any())).thenReturn(Optional.of(customer));
        assertThat(tested.getCustomerByMail("")).isEqualTo(customer);
    }

    @Test
    void shouldThrowExceptionWhenNotFound(){
        when(CUSTOMER_REPOSITORY_PERSISTENCE.findByMail(any())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> tested.getCustomerByMail("")).isExactlyInstanceOf(CustomerNotFoundException.class);
    }

}
