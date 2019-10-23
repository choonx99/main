package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyExpenseList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.exchangedata.ExchangeData;

/**
 * Manages storage of ExpenseList data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ExpenseListStorage expenseListStorage;
    private ExchangeDataStorage exchangeDataStorage;
    private UserPrefsStorage userPrefsStorage;


    public StorageManager(ExpenseListStorage expenseListStorage, ExchangeDataStorage exchangeDataStorage,
                          UserPrefsStorage userPrefsStorage) {
        super();
        this.expenseListStorage = expenseListStorage;
        this.exchangeDataStorage = exchangeDataStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ ExpenseList methods ==============================

    @Override
    public Path getExpenseListFilePath() {
        return expenseListStorage.getExpenseListFilePath();
    }

    @Override
    public Path getExchangeDataFilePath() {
        return null;
    }

    @Override
    public Optional<ExchangeData> readExchangeData() throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: ");
        return exchangeDataStorage.readExchangeData();
    }

    @Override
    public Optional<ExchangeData> readExchangeData(Path filePath) throws DataConversionException, IOException {
        return exchangeDataStorage.readExchangeData(filePath);
    }

    @Override
    public Optional<ReadOnlyExpenseList> readExpenseList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return expenseListStorage.readExpenseList(filePath);
    }

    @Override
    public Optional<ReadOnlyExpenseList> readExpenseList() throws DataConversionException, IOException {
        return readExpenseList(expenseListStorage.getExpenseListFilePath());
    }

    @Override
    public void saveExpenseList(ReadOnlyExpenseList expenseList) throws IOException {
        saveExpenseList(expenseList, expenseListStorage.getExpenseListFilePath());
    }

    @Override
    public void saveExpenseList(ReadOnlyExpenseList expenseList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        expenseListStorage.saveExpenseList(expenseList, filePath);
    }

}
