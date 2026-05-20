package africa.semicolon.hotelmanagement.data.repositories;

import africa.semicolon.hotelmanagement.data.models.Guest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class GuestRepositoryImplTest {

    private GuestRepositoryImpl guestRepo;
    private Guest guest;

    @BeforeEach
    void setUp() {
        guestRepo = new GuestRepositoryImpl();
        guest = new Guest();
        guest.setId(1);
        guest.setFirstName("Victor");
        guest.setEmergencyContact("08166529190");
    }

    @Test
    void addGuest_newGuest_increasesCount() {
        guestRepo.addGuest(guest);
        assertEquals(1, guestRepo.getCount());
    }

    @Test
    void addGuest_newGuest_guestIsStoredCorrectly() {
        guestRepo.addGuest(guest);
        Guest found = guestRepo.getGuestById(1);
        assertNotNull(found);
        assertEquals("Victor", found.getFirstName());
        assertEquals("08166529190", found.getEmergencyContact());
    }

    @Test
    void addGuest_multipleGuests_countMatchesUniqueGuests() {
        Guest guest2 = new Guest();
        guest2.setId(2);
        guest2.setFirstName("Ada");

        guestRepo.addGuest(guest);
        guestRepo.addGuest(guest2);
        guestRepo.addGuest(new Guest());

        assertEquals(3, guestRepo.getCount());
    }

    @Test
    void addXGuestWithAutoId_addXYGuestAutoId_repoUpdatesXGuest() {

        guest.setId(1);
        guest.setFirstName("victor");
        guest.setEmergencyContact("08166529190");
        guestRepo.addGuest(guest);

        guest.setEmergencyContact("08055465240");
        guestRepo.addGuest(guest);

        guestRepo.addGuest(new Guest());

        assertEquals(2, guestRepo.getCount());
    }

    @Test
    void addGuest_duplicateId_updatesGuestNotDuplicate() {
        guestRepo.addGuest(guest);

        Guest updated = new Guest();
        updated.setId(1);
        updated.setFirstName("UpdatedVictor");
        updated.setEmergencyContact("08055465240");
        guestRepo.addGuest(updated);

        assertEquals(1, guestRepo.getCount());  // still 1, not 2
        assertEquals("UpdatedVictor", guestRepo.getGuestById(1).getFirstName());
    }

    @Test
    void getGuestById_existingId_returnsCorrectGuest() {
        guestRepo.addGuest(guest);
        Guest found = guestRepo.getGuestById(1);
        assertNotNull(found);
        assertEquals(1, found.getId());
        assertEquals("Victor", found.getFirstName());
    }

    @Test
    void getGuestById_nonExistentId_returnsNull() {
        guestRepo.addGuest(guest);
        Guest found = guestRepo.getGuestById(99);
        assertNull(found);
    }

    @Test
    void getGuestById_emptyRepo_returnsNull() {
        Guest found = guestRepo.getGuestById(1);
        assertNull(found);
    }

    @Test
    void getAllGuests_emptyRepo_returnsEmptyList() {
        List<Guest> all = guestRepo.getAllGuests();
        assertNotNull(all);
        assertTrue(all.isEmpty());
    }

    @Test
    void getAllGuests_multipleGuests_returnsAll() {
        Guest guest2 = new Guest();
        guest2.setId(2);
        guest2.setFirstName("Ada");

        guestRepo.addGuest(guest);
        guestRepo.addGuest(guest2);

        List<Guest> all = guestRepo.getAllGuests();
        assertEquals(2, all.size());
    }

    @Test
    void getAllGuests_returnsDefensiveCopy_mutationDoesNotAffectRepo() {
        guestRepo.addGuest(guest);
        List<Guest> all = guestRepo.getAllGuests();
        all.clear();

        assertEquals(1, guestRepo.getCount()); // repo unaffected
    }

    @Test
    void updateGuest_existingGuest_fieldsAreUpdated() {
        guestRepo.addGuest(guest);

        guest.setFirstName("UpdatedVictor");
        guest.setEmergencyContact("07011223344");
        guestRepo.updateGuest(guest);

        Guest updated = guestRepo.getGuestById(1);
        assertEquals("UpdatedVictor", updated.getFirstName());
        assertEquals("07011223344", updated.getEmergencyContact());
    }

    @Test
    void updateGuest_existingGuest_countDoesNotChange() {
        guestRepo.addGuest(guest);
        int before = guestRepo.getCount();

        guest.setFirstName("NewName");
        guestRepo.updateGuest(guest);

        assertEquals(before, guestRepo.getCount());
    }

    @Test
    void updateGuest_nonExistentGuest_throwsException() {
        Guest ghost = new Guest();
        ghost.setId(99);
        ghost.setFirstName("Nobody");

        assertThrows(NoSuchElementException.class, () -> guestRepo.updateGuest(ghost));
    }


    @Test
    void deleteGuest_existingGuest_removesFromRepo() {
        guestRepo.addGuest(guest);
        guestRepo.deleteGuest(1);

        assertNull(guestRepo.getGuestById(1));
    }

    @Test
    void deleteGuest_existingGuest_decreasesCount() {
        guestRepo.addGuest(guest);
        guestRepo.deleteGuest(1);

        assertEquals(0, guestRepo.getCount());
    }

    @Test
    void deleteGuest_nonExistentId_throwsException() {
        assertThrows(NoSuchElementException.class, () -> guestRepo.deleteGuest(99));
    }

    @Test
    void deleteGuest_oneOfManyGuests_othersUnaffected() {
        Guest guest2 = new Guest();
        guest2.setId(2);
        guest2.setFirstName("Ada");

        guestRepo.addGuest(guest);
        guestRepo.addGuest(guest2);
        guestRepo.deleteGuest(1);

        assertEquals(1, guestRepo.getCount());
        assertNull(guestRepo.getGuestById(1));
        assertNotNull(guestRepo.getGuestById(2));
    }



    @Test
    void getCount_emptyRepo_returnsZero() {
        assertEquals(0, guestRepo.getCount());
    }

    @Test
    void getCount_afterAddAndDelete_returnsCorrectCount() {
        Guest guest2 = new Guest();
        guest2.setId(2);

        guestRepo.addGuest(guest);
        guestRepo.addGuest(guest2);
        guestRepo.deleteGuest(1);

        assertEquals(1, guestRepo.getCount());
    }
}