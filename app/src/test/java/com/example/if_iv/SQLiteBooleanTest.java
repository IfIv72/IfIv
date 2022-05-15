package com.example.if_iv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.if_iv.util.MegaClase;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SQLiteBooleanTest {
    @Test
    public void isTrueTest() {
        assertTrue(MegaClase.gestionInt(1));
    }

    @Test
    public void isFalseTest() {
        assertFalse(MegaClase.gestionInt(0));
    }
}