package service;

import in.CheckoutUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import out.CartRepository;
import out.ClientRepository;

@Service
@AllArgsConstructor
public class CheckoutService implements CheckoutUseCase {

    private ClientRepository clientRepository;
    private CartRepository cartRepository;

    @Override
    public void checkout(long clientId) {

    }

    @Override
    public void cancelCheckout(long clientId) {

    }

    @Override
    public void confirmPayment(long cartId) {

    }

    @Override
    public void rollbackCheckout(long cartId) {

    }
}
