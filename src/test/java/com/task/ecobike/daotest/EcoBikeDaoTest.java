package com.task.ecobike.daotest;

import com.task.ecobike.dao.EcoBikeDao;
import com.task.ecobike.dto.ProductsDTO;
import org.junit.Assert;
import org.junit.Test;

public class EcoBikeDaoTest {
    private EcoBikeDao ecoBikeDao = new EcoBikeDao();

    @Test
    public void testSaveToFileWithoutChangesExceptedFalse() {
        Assert.assertFalse(ecoBikeDao.saveToFile(true, new ProductsDTO()));
    }
}
