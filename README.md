# 🏦 Banking System — Java Swing Application

A simple desktop banking application built with Java and Swing (Java GUI). It reads customer account data from a CSV file and provides a clean GUI for performing core banking operations.

---

## 📋 Features

- **View All Accounts** — Display all customer accounts with their names, account numbers, and current balances
- **Deposit** — Add funds to any account by account number
- **Withdraw** — Remove funds from an account (with insufficient balance protection)
- **Transfer** — Move funds between two accounts seamlessly

---

## 🗂️ Project Structure

```
banksystem3/
│
├── src/banksystem3/
│   ├── Main.java          # Entry point — loads accounts and launches GUI
│   ├── GUI.java           # Swing-based graphical user interface
│   ├── Account.java       # Account model (extends Customer)
│   ├── Customer.java      # Base customer model (first/last name)
│   ├── Transaction.java   # Handles fund transfers between accounts
│   └── ReadAccounts.java  # Parses account data from CSV file
│
├── Accounts.csv           # Sample account data (FirstName, LastName, AccNum, Balance)
└── src/module-info.java
```

---

## 🚀 Getting Started

### Prerequisites
- Java 11 or later
- An IDE such as Eclipse or IntelliJ IDEA

### Running the Project
1. Clone or download this repository
2. Open in your preferred Java IDE
3. Make sure `Accounts.csv` is in the project root directory
4. Run `Main.java`

---

## 📄 CSV Format

The `Accounts.csv` file should follow this format:

```
FirstName, LastName, AccountNumber, Balance
John, Doe, 1001, 5000
Jane, Smith, 1002, 3200
```

---

## 🛠️ Technologies Used

- **Java** — Core language
- **Java Swing** — GUI framework
- **CSV** — Data storage for account records

---

## 📌 Notes

- All account data is loaded into memory at startup; changes are **not** persisted back to the CSV file
- Balance validation is enforced on withdrawals and transfers to prevent negative balances

---

## 👤 Author

Built as a Java OOP project demonstrating inheritance, file I/O, and GUI event handling.
