package workshop;

public class FizzPatternMatcher implements PatternMatcher{

    @Override
    public boolean matches(int number) {
        // TODO Auto-generated method stub
        return number % 3 == 0;
    }

    @Override
    public String generateResponse() {
        // TODO Auto-generated method stub
        return "Fizz";
    }


}

