package hello.core.order;

public class Order {
    private int itemPrice;
    private Long memberId;
    private String itemName;

    public int calculatePrice(){
        return itemPrice - discountPrice ;

    }
    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    private int discountPrice;

    public Order(Long memberId, String itemName, int itemPrice, int discountPrice) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.discountPrice = discountPrice;
        this.itemPrice = itemPrice;

    }

    @Override
    public String toString() {
        return "Order{" +
                "itemPrice=" + itemPrice +
                ", memberId=" + memberId +
                ", itemName='" + itemName + '\'' +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
