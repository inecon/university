package ua.com.foxminded.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GroupTest {

    public Group VALID_GROUP = new Group(1, "Group01", "Math");

    @Test
    public void shouldCreateGroup() {
        Group actualGroup = mock(Group.class);
        actualGroup.setId(1);
        actualGroup.setTitle("Group01");
        actualGroup.setDescription("Math");

        verify(actualGroup).setId(1);
        verify(actualGroup).setTitle("Group01");
        verify(actualGroup).setDescription("Math");
    }

    @Test
    public void shouldCreateValidGroup() {
        Group actualGroup = new Group();
        actualGroup.setId(1);
        actualGroup.setTitle("Group01");
        actualGroup.setDescription("Math");
        Group expectedGroup = VALID_GROUP;
        assertEquals(actualGroup, expectedGroup);
    }

}