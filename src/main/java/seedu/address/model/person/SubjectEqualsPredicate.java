package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests whether a {@code Person}'s subject matches the specified subject.
 */
public class SubjectEqualsPredicate implements Predicate<Person> {
    private final Subject subject;

    public SubjectEqualsPredicate(Subject subject) {
        this.subject = subject;
    }

    @Override
    public boolean test(Person person) {
        return StringUtil.containsWordPrefixIgnoreCase(person.getSubject().toString(), subject.subject);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof SubjectEqualsPredicate)) {
            return false;
        }
        SubjectEqualsPredicate otherPredicate = (SubjectEqualsPredicate) other;
        return subject.subject.equalsIgnoreCase(otherPredicate.subject.subject);
    }
}
