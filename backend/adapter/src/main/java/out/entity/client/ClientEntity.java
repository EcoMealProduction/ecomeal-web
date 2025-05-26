package out.entity.client;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import out.entity.cart.CartEntity;
import out.entity.payment.BankingDetailsEntity;
import out.entity.user.UserAccountEntity;

@Entity
@Table(name = "client")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToOne
    @JoinColumn(name = "user_account_id", nullable = false, unique = true)
    private UserAccountEntity userAccount;

    @ManyToOne
    @JoinColumn(name = "banking_details_id")
    private BankingDetailsEntity bankingDetails;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private CartEntity cart;

    public ClientEntity(
            String firstName, String lastName, UserAccountEntity userAccount,
            BankingDetailsEntity bankingDetails, CartEntity cart) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.userAccount = userAccount;
        this.bankingDetails = bankingDetails;
        this.cart = cart;
    }

    public ClientEntity() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserAccountEntity getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccountEntity userAccount) {
        this.userAccount = userAccount;
    }

    public BankingDetailsEntity getBankingDetails() {
        return bankingDetails;
    }

    public void setBankingDetails(BankingDetailsEntity bankingDetails) {
        this.bankingDetails = bankingDetails;
    }

    public CartEntity getCart() {
        return cart;
    }

    public void setCart(CartEntity cart) {
        this.cart = cart;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
