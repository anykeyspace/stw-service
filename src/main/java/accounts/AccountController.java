package accounts;

public class AccountController implements ControllerMBean {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public Integer getUserLimit() {
        return accountService.getUsersLimit();
    }

    @Override
    public void setUserLimit(Integer userLimit) {
        accountService.setUsersLimit(userLimit);
    }
}
