package workshop;

public class BuzzPatternMatcher implements PatternMatcher{

    @Override
    public boolean matches(int number) {
        // TODO Auto-generated method stub
        return number % 5 == 0;
    }

    @Override
    public String generateResponse() {
        // TODO Auto-generated method stub
        return "Buzz";
    }
}

