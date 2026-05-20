package africa.semicolon.hotelmanagement.data.repositories;

import africa.semicolon.hotelmanagement.data.models.Guest;

import java.util.List;

public interface GuestRepository {
    void addGuest(Guest guest);
    Guest getGuestById(long id);
    List<Guest> getAllGuests();
    void updateGuest(Guest guest);
    void deleteGuest(long id);
    int getCount();
}
