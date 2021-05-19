import java.time.LocalDate;

 class Journal {
    private int id;
    private String title;
    private String description;
    private LocalDate date;
    private int priceofSubscribe;

    public Journal(int id, String title, String description, LocalDate date, int priceofSubscribe) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.priceofSubscribe = priceofSubscribe;
    }

    public Journal(String title, String description, LocalDate date, int priceofSubscribe) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.priceofSubscribe = priceofSubscribe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getPriceofSubscribe() {
        return priceofSubscribe;
    }

    public void setPriceofSubscribe(int priceofSubscribe) {
        this.priceofSubscribe = priceofSubscribe;
    }

    @Override
    public String toString() {
        if (id == 0)
            return "Журнал \"" + title + "\", " + description + ", Дата выхода: " + date + ", Цена подписки: "
                    + priceofSubscribe / 100 + "." + String.format("%02d", priceofSubscribe % 100) + " грн.";
        else
            return "Magazine ID#" + id + ": Журнал \"" + title + "\", " + description + ", Дата выхода: " + date
                    + ", Цена подписки: " + priceofSubscribe / 100 + "." + String.format("%02d", priceofSubscribe % 100)
                    + " грн.";
    }
}