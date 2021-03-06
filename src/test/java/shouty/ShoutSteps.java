package shouty;

import java.util.List;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static java.util.Collections.emptyList;
import static org.junit.Assert.*;


public class ShoutSteps {
    private final ShoutyServiceWrapper shouty;
    private List<Shout> shouts;

    public ShoutSteps(ShoutyServiceWrapper shouty_){
        shouty = shouty_;
    }

    @Given("{word} is at {int}, {int}")
    public void person_is_at(String person, Integer x, Integer y) {
        shouty.setLocation(person, new Location(x, y));
    }

    @When("{word} shouts")
    public void person_shouts(String shouter) {
        shouty.shout(new Shout(shouter, "Hello, world"));
    }

    @When("{word}'s shout originates from {int}, {int}")
    public void sean_s_shout_originates_from(String shouter, Integer x, Integer y) {
        shouty.shout(new Shout(shouter, "Hello, world", x, y));
    }

    @Then("{word} should hear {word}")
    public void listener_should_hear_shouter(String listener, String shouter) {
        List<Shout> shouts = shouty.getShoutsHeardBy(listener);

        assertEquals(shouter, shouts.get(0).person);
    }

    @Then("{word} should not hear {word}")
    public void listener_should_not_hear_shouter(String listener, String shouter) {
        List<Shout> shouts = shouty.getShoutsHeardBy(listener);

        assertEquals(0, shouts.size());
    }
}
