package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class SubjectEqualsPredicateTest {

    @Test
    public void equals_sameSubject_sameObject() {
        Subject subj = new Subject("Biology");
        SubjectEqualsPredicate p = new SubjectEqualsPredicate(subj);
        assertTrue(p.equals(p));
    }

    @Test
    public void test_subjectPrefixMatch_returnsTrue() {
        SubjectEqualsPredicate predicate = new SubjectEqualsPredicate(new Subject("Bio"));
        assertTrue(predicate.test(new PersonBuilder().withSubject("Biology").build()));
        assertTrue(predicate.test(new PersonBuilder().withSubject("Biology Advanced").build()));
        assertTrue(predicate.test(new PersonBuilder().withSubject("bio lab").build())); // case-insensitive
    }

    @Test
    public void test_subjectPrefixMatch_returnsFalse() {
        SubjectEqualsPredicate predicate = new SubjectEqualsPredicate(new Subject("Bio"));
        // mid-word substring should not match
        assertFalse(predicate.test(new PersonBuilder().withSubject("Microbiology").build()));
        // completely different
        assertFalse(predicate.test(new PersonBuilder().withSubject("Math").build()));
    }
}

