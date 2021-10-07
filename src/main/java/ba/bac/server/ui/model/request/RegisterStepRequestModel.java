package ba.bac.server.ui.model.request;

public class RegisterStepRequestModel {
    private int registerStep;
    private String userId;

    public int getRegisterStep() {
        return registerStep;
    }

    public void setRegisterStep(int registerStep) {
        this.registerStep = registerStep;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
