import java.util.*;

class Feedback {
    private int id;
    private int customerId;
    private String feedbackType;
    private int rating;
    private String comments;

    // Constructor
    public Feedback(int id, int customerId, String feedbackType, int rating, String comments) {
        this.id = id;
        this.customerId = customerId;
        this.feedbackType = feedbackType;
        this.rating = rating;
        this.comments = comments;
    }

    // Getters and Setters
    public int getId() { return id; }
    public int getCustomerId() { return customerId; }
    public String getFeedbackType() { return feedbackType; }
    public int getRating() { return rating; }
    public String getComments() { return comments; }

    public void setFeedbackType(String feedbackType) { this.feedbackType = feedbackType; }
    public void setRating(int rating) { this.rating = rating; }
    public void setComments(String comments) { this.comments = comments; }

    // Display Feedback Info
    public void displayInfo() {
        System.out.println("Feedback ID: " + id + ", Customer ID: " + customerId + ", Type: " + feedbackType +
                ", Rating: " + rating + ", Comments: " + comments);
    }
}

public class CustomerFeedbackSystem {
    private static List<Feedback> feedbackList = new ArrayList<>();
    private static int feedbackIdCounter = 1;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Customer Feedback and Support System ---");
            System.out.println("1. Add Feedback");
            System.out.println("2. View All Feedback");
            System.out.println("3. Search Feedback by ID");
            System.out.println("4. Update Feedback");
            System.out.println("5. Delete Feedback");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: addFeedback(); break;
                case 2: viewFeedback(); break;
                case 3: searchFeedback(); break;
                case 4: updateFeedback(); break;
                case 5: deleteFeedback(); break;
                case 6: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // Method to add a new feedback
    private static void addFeedback() {
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Feedback Type (Positive/Negative): ");
        String feedbackType = scanner.nextLine();
        System.out.print("Enter Rating (1-5): ");
        int rating = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Comments: ");
        String comments = scanner.nextLine();

        Feedback newFeedback = new Feedback(feedbackIdCounter++, customerId, feedbackType, rating, comments);
        feedbackList.add(newFeedback);
        System.out.println("Feedback Added Successfully!");
    }

    // Method to view all feedback
    private static void viewFeedback() {
        if (feedbackList.isEmpty()) {
            System.out.println("No feedback records found.");
            return;
        }
        System.out.println("\n--- Feedback Records ---");
        for (Feedback feedback : feedbackList) {
            feedback.displayInfo();
        }
    }

    // Method to search for feedback by ID
    private static void searchFeedback() {
        System.out.print("Enter Feedback ID to search: ");
        int id = scanner.nextInt();

        for (Feedback feedback : feedbackList) {
            if (feedback.getId() == id) {
                System.out.println("Feedback Found:");
                feedback.displayInfo();
                return;
            }
        }
        System.out.println("Feedback Not Found.");
    }

    // Method to update a feedback record
    private static void updateFeedback() {
        System.out.print("Enter Feedback ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (Feedback feedback : feedbackList) {
            if (feedback.getId() == id) {
                System.out.print("Enter New Feedback Type (Positive/Negative, leave blank to keep unchanged): ");
                String feedbackType = scanner.nextLine();
                if (!feedbackType.isEmpty()) feedback.setFeedbackType(feedbackType);

                System.out.print("Enter New Rating (1-5, -1 to keep unchanged): ");
                int rating = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (rating != -1) feedback.setRating(rating);

                System.out.print("Enter New Comments (leave blank to keep unchanged): ");
                String comments = scanner.nextLine();
                if (!comments.isEmpty()) feedback.setComments(comments);

                System.out.println("Feedback Updated Successfully!");
                return;
            }
        }
        System.out.println("Feedback Not Found.");
    }

    // Method to delete a feedback record
    private static void deleteFeedback() {
        System.out.print("Enter Feedback ID to delete: ");
        int id = scanner.nextInt();

        Iterator<Feedback> iterator = feedbackList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
                System.out.println("Feedback Deleted Successfully!");
                return;
            }
        }
        System.out.println("Feedback Not Found.");
    }
}
