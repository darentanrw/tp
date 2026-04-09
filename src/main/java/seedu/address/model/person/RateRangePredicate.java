package seedu.address.model.person;

import java.math.BigInteger;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Rate} is within the given inclusive range.
 */
public class RateRangePredicate implements Predicate<Person> {
    private final BigInteger lowerBound;
    private final BigInteger upperBound;

    /**
     * Creates a predicate that checks whether a person's rate is between
     * {@code lowerBound} and {@code upperBound}, inclusive.
     */
    public RateRangePredicate(BigInteger lowerBound, BigInteger upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public boolean test(Person person) {
        Rate rate = person.getRate();
        return rate.compareNumericValueTo(lowerBound) >= 0 && rate.compareNumericValueTo(upperBound) <= 0;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof RateRangePredicate)) {
            return false;
        }

        RateRangePredicate otherPredicate = (RateRangePredicate) other;
        return lowerBound.equals(otherPredicate.lowerBound)
                && upperBound.equals(otherPredicate.upperBound);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("lowerBound", lowerBound)
                .add("upperBound", upperBound)
                .toString();
    }
}
