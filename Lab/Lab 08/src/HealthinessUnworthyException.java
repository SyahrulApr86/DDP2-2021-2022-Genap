public class HealthinessUnworthyException  extends Throwable {
    // Constructor Jika tidak ada parameter
    public HealthinessUnworthyException() {
        super();
    }

    // Constructor Jika ada parameter
    public HealthinessUnworthyException(String message) {
        super(message);
    }
}
