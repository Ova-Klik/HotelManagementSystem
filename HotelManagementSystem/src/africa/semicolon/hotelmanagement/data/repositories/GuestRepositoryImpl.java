package africa.semicolon.hotelmanagement.data.repositories;

import africa.semicolon.hotelmanagement.data.models.Guest;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class GuestRepositoryImpl implements GuestRepository {

    private final List<Guest> guests = new ArrayList<>();
    private int count = 0;

    @Override
    public void addGuest(Guest guest) {
        for (int i = 0; i < guests.size(); i++) {
            if (guests.get(i).getId() == guest.getId()) {
                guests.set(i, guest);
                return;
            }
        }
        guests.add(guest);
        count++;
    }

    @Override
    public Guest getGuestById(long id) {
                for (Guest g : guests) {
                    if (g.getId() == id) {
                        return g;
                    }
                }
                return null;
            }

    @Override
    public List<Guest> getAllGuests() {
        return new ArrayList<>(guests);
    }

    @Override
    public void updateGuest(Guest guest) {
        for (int i = 0; i < guests.size(); i++) {
            if (guests.get(i).getId() == guest.getId()) {
                guests.set(i, guest);
                return;
            }
        }
        throw new NoSuchElementException("Guest with id " + guest.getId() + " not found");
    }

    @Override
    public void deleteGuest(long id) {
            boolean removed = guests.removeIf(g -> g.getId() == id);
            if (removed) {
                count--;
            } else {
                throw new NoSuchElementException("Guest with id " + id + " not found");
            }
    }

    @Override
    public int getCount() {
        return count;
    }
}

