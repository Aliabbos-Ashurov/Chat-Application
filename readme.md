# CHAT APPLICATION 💬

## Description

This project is a backend application developed using Java. It provides various services and functionalities for managing users, groups, chats, messages, posts, subscribers, etc.

## Features

- **User management**: Add, update, delete, search, and authenticate users.
- **Group management**: Create, update, delete, and search groups. Retrieve owner groups.
- **Chat management**: Get or create chats between users. Retrieve user chats and group chats.
- **Message management**: Send, receive, and retrieve messages within chats or groups.
- **Post management**: Create, update, delete, and search posts within channels.
- **Subscriber management**: Subscribe users to channels and manage their roles.
- **Confirmation management**: Send confirmation emails and generate confirmation codes.
- **Base services**: Define basic operations for entity management.

## Technologies Used

- Java
- Maven

```java
public boolean emailSender(UUID userID, String email, MailType mailType) {
        ConfirmationEmail confirmationEmail = getOrCreate(userID, email,mailType);
        if (Objects.isNull(confirmationEmail)) return false;
        Properties properties = getProperties();
        Session session = getSession(properties);
        dispatchConfirmationCode(email, session, confirmationEmail.getCode());
        return true;
}
public Integer getConfirmationCodeByUser(UUID userID,MailType mailType) {
    List<ConfirmationEmail> emails = repository.getAll();
    Optional<ConfirmationEmail> confirmationEmail = emails.stream()
            .filter(email -> email.getUserID().equals(userID) && email.getMailType().equals(mailType))
            .findFirst();
    if (confirmationEmail.isPresent()) return confirmationEmail.get().getCode();
    else return 0;
}
```
## Installation

1. Clone the repository to your local machine.
2. Configure your database settings in the application.properties file.
3. Build the project using Maven.
4. Run the application.

## Usage

1. Access the provided endpoints using a REST client or integrate them into your frontend application.
2. Utilize the provided functionalities for managing users, groups, chats, messages, posts, subscribers, etc.
3. Explore and customize the application as needed for your specific requirements.

## Contributing

Contributions are welcome! Please fork the repository, make your changes, and submit a pull request.

## License

MIT License

Feel free to customize this README according to your project's specifics and requirements! Let me know if you need further assistance.

You may download from this link: [DOWNLOAD FROM GITHUB](https://github.com/Aliabbos-Ashurov/Chat-Application)