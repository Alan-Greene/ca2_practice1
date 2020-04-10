package ca_practice;

public class Dvd {
    private String title, category;
    private Integer year, quantityInStock, quantityCheckedOut, ratingCount;
    private Double ratingTotal;
    private final Integer STARTING_COPIES = 5;

    public Dvd(String title, String category, Integer year, Integer quantityInStock, Integer quantityCheckedOut) {
        setTitle(title);
        setCategory(category);
        setYear(year);
        setQuantityInStock(quantityInStock);
        setQuantityCheckedOut(quantityCheckedOut);
        setRatingTotal(0.0);
        setRatingCount(0);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public void setQuantityCheckedOut(Integer quantityCheckedOut) {
        this.quantityCheckedOut = quantityCheckedOut;
    }

    public void setRatingTotal(Double ratingTotal) {
        this.ratingTotal = ratingTotal;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public Integer getQuantityCheckedOut() {
        return quantityCheckedOut;
    }

    public Double getRatingTotal() {
        return ratingTotal;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public Integer getSTARTING_COPIES() {
        return STARTING_COPIES;
    }

    public void rateDvd(Double rating){
        if (rating >=1 && rating <=5){
            ratingCount ++;
            ratingTotal = ratingTotal + rating;
        } else {
            System.out.println("Rating must be between 1 and 5");
        }
    }

    public Double getAverageRating() throws ArithmeticException{
        if (ratingCount == 0){
            throw new ArithmeticException("There are 0 ratings for: " + title);
        }
        return ratingTotal / ratingCount;
    }

    public void checkOut() throws OutOfStockException{
        if (quantityInStock <=0){
            throw new OutOfStockException("We are currently out of stock.");
        }
        quantityInStock -= 1;
        quantityCheckedOut += 1;
    }

    public boolean returnDvd(){
        if (quantityInStock >= STARTING_COPIES){
            System.out.println("That DVD does not belong to this store.");
            return false;
        }

        quantityInStock ++;
        quantityCheckedOut --;
        return true;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(title);
        builder.append(" ");
        builder.append(category);
        builder.append(" ");
        builder.append(year);
        builder.append(" ");
        builder.append(quantityInStock);
        builder.append(" ");
        builder.append(quantityCheckedOut);
        builder.append(" ");
        builder.append(ratingTotal);
        builder.append(" ");
        builder.append(ratingCount);

        return builder.toString();
    }


}
