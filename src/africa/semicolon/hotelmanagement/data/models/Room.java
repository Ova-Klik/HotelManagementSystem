package africa.semicolon.hotelmanagement.data.models;

public class Room {
    private long id;
    private int roomNumber;
    private RoomStatus status;
    private int floor;
    private String name;
    private String description;
    private Category roomCategory;
    private BedType bedType;
    private ViewType viewType;
    private int MaxOccupancy;
    private int guestId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(Category roomCategory) {
        this.roomCategory = roomCategory;
    }

    public BedType getBedType() {
        return bedType;
    }

    public void setBedType(BedType bedType) {
        this.bedType = bedType;
    }

    public ViewType getViewType() {
        return viewType;
    }

    public void setViewType(ViewType viewType) {
        this.viewType = viewType;
    }

    public int getMaxOccupancy() {
        return MaxOccupancy;
    }

    public void setMaxOccupancy(int maxOccupancy) {
        MaxOccupancy = maxOccupancy;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }
}
