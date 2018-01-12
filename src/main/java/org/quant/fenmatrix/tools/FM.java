package org.quant.fenmatrix.tools;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class FM {

  

  /**
   * Throws an error if a <b>pre-condition</b> is not verified
   * <p>
   * @param condition is a condition to be verified
   * @param format 
   * @param objects 
   * @param message is a message emitted.
   * @throws RuntimeException 
   * @throws a FenException if the condition is not met
   */
  public static void require(
      final boolean condition,
      final String format,
      final Object... objects) throws RuntimeException {
    if (!condition)
      throw new FMException(String.format(format, objects));
  }

  /**
   * Throws an error if a <b>pre-condition</b> is not verified
   * <p>
   * @param condition is a condition to be verified
   * @param message is a message emitted.
   * @throws RuntimeException 
   * @throws a FenException if the condition is not met
   */
  public static void require(
      final boolean condition,
      final String message) throws RuntimeException {
    if (!condition)
      throw new FMException(message);
  }

  /**
   * Throws an error if a <b>pre-condition</b> is not verified
   * <p>
   * @param condition is a condition to be verified
   * @param klass is a Class which extends RuntimeException
   * @param message is a message emitted.
   * @throws RuntimeException 
   * @throws a FenException if the condition is not met
   */
  @SuppressWarnings("javadoc")
  public static void require(
      final boolean condition,
      final Class<? extends RuntimeException> klass,
      final String message) throws RuntimeException {
    if (!condition) {
      try {
        final Constructor<? extends RuntimeException> c = klass.getConstructor(String.class);
        throw c.newInstance(message);
      } catch (final SecurityException e) {
        e.printStackTrace();
      } catch (final NoSuchMethodException e) {
        e.printStackTrace();
      } catch (final IllegalArgumentException e) {
        e.printStackTrace();
      } catch (final InstantiationException e) {
        e.printStackTrace();
      } catch (final IllegalAccessException e) {
        e.printStackTrace();
      } catch (final InvocationTargetException e) {
        e.printStackTrace();
      }
    }
  }

  public static void ensure(
      final boolean condition,
      final String format,
      final Object... objects) throws RuntimeException {
    if (!condition)
      throw new FMException(String.format(format, objects));
  }

  /**
   * Throws an error if a <b>post-condition</b> is not verified
   * <p>
   * @note  this method should <b>never</b> be removed from byte code by AspectJ.
   *        If you do so, you must be plenty sure of effects and risks of this decision.
   * <p>
   * @param condition is a condition to be verified
   * @param message is a message emitted.
   * @throws RuntimeException 
   * @throws a FenException if the condition is not met
   */
  public static void ensure(
      final boolean condition,
      final String message) throws RuntimeException {
    if (!condition)
      throw new FMException(message);
  }

  /**
   * This method unconditionally emits a message to the logging system but does not throw any exception.
   *
   * @param message is a message to be emitted
   */
  public static void error(final String message) {
    if (FenMatrix.logger != null) {
      FenMatrix.logger.error(message);
    } else {
      System.err.printf("ERROR: %s\n", message);
    }
  }

  /**
   * This method unconditionally emits a message to the logging system but does not throw any exception.
   *
   * @param message is a message to be emitted
   */
  public static void error(final String message, final Throwable t) {
    if (FenMatrix.logger != null) {
      FenMatrix.logger.error(message, t);
    } else {
      System.err.printf("ERROR: %s : %s\n", message, t.getMessage());
      t.printStackTrace(System.err);
    }
  }

  /**
   * This method unconditionally emits a message to the logging system but does not throw any exception.
   *
   * @param message is a message to be emitted
   */
  public static void error(final Throwable t) {
    if (FenMatrix.logger != null) {
      FenMatrix.logger.error(t.getMessage(), t);
    } else {
      System.err.printf("ERROR: %s\n", t.getMessage());
      System.err.println(t.getMessage());
      t.printStackTrace(System.err);
    }
  }

  /**
   * This method unconditionally emits a message to the logging system but does not throw any exception.
   *
   * @param message is a message to be emitted
   */
  public static void warn(final String message) {
    if (FenMatrix.logger != null) {
      FenMatrix.logger.warn(message);
    } else {
      System.err.printf("WARN: %s\n", message);
    }
  }

  /**
   * This method unconditionally emits a message to the logging system but does not throw any exception.
   *
   * @param message is a message to be emitted
   */
  public static void warn(final String message, final Throwable t) {
    if (FenMatrix.logger != null) {
      FenMatrix.logger.warn(message, t);
    } else {
      System.err.printf("WARN: %s : %s\n", message, t.getMessage());
      t.printStackTrace(System.err);
    }
  }

  /**
   * This method unconditionally emits a message to the logging system but does not throw any exception.
   *
   * @param message is a message to be emitted
   */
  public static void warn(final Throwable t) {
    if (FenMatrix.logger != null) {
      FenMatrix.logger.warn(t.getMessage(), t);
    } else {
      System.err.printf("WARN: %s\n", t.getMessage());
      System.err.println(t.getMessage());
      t.printStackTrace(System.err);
    }
  }

  /**
   * This method unconditionally emits a message to the logging system but does not throw any exception.
   *
   * @param message is a message to be emitted
   */
  public static void info(final String message) {
    if (FenMatrix.logger != null) {
      FenMatrix.logger.info(message);
    } else {
      System.err.printf("INFO: %s\n", message);
    }
  }

  /**
   * This method unconditionally emits a message to the logging system but does not throw any exception.
   *
   * @param message is a message to be emitted
   */
  public static void info(final String message, final Throwable t) {
    if (FenMatrix.logger != null) {
      FenMatrix.logger.info(message, t);
    } else {
      System.err.printf("INFO: %s : %s\n", message, t.getMessage());
      t.printStackTrace(System.err);
    }
  }

  /**
   * This method unconditionally emits a message to the logging system but does not throw any exception.
   *
   * @param message is a message to be emitted
   */
  public static void info(final Throwable t) {
    if (FenMatrix.logger != null) {
      FenMatrix.logger.info(t.getMessage(), t);
    } else {
      System.err.printf("INFO: %s\n", t.getMessage());
      System.err.println(t.getMessage());
      t.printStackTrace(System.err);
    }
  }

  /**
   * This method unconditionally emits a message to the logging system but does not throw any exception.
   *
   * @param message is a message to be emitted
   */
  public static void debug(final String message) {
    if (FenMatrix.logger != null) {
      FenMatrix.logger.debug(message);
    } else {
      System.err.printf("DEBUG: %s\n", message);
    }
  }

  /**
   * This method unconditionally emits a message to the logging system but does not throw any exception.
   *
   * @param message is a message to be emitted
   */
  public static void debug(final String message, final Throwable t) {
    if (FenMatrix.logger != null) {
      FenMatrix.logger.debug(message, t);
    } else {
      System.err.printf("DEBUG: %s : %s\n", message, t.getMessage());
      t.printStackTrace(System.err);
    }
  }

  /**
   * This method unconditionally emits a message to the logging system but does not throw any exception.
   *
   * @param message is a message to be emitted
   */
  public static void debug(final Throwable t) {
    if (FenMatrix.logger != null) {
      FenMatrix.logger.debug(t.getMessage(), t);
    } else {
      System.err.printf("DEBUG: %s\n", t.getMessage());
      System.err.println(t.getMessage());
      t.printStackTrace(System.err);
    }
  }

  /**
   * This method unconditionally emits a message to the logging system but does not throw any exception.
   *
   * @param message is a message to be emitted
   */
  public static void trace(final String message) {
    if (FenMatrix.logger != null) {
      FenMatrix.logger.trace(message);
    } else {
      System.err.printf("TRACE: %s\n", message);
    }
  }

  /**
   * This method unconditionally emits a message to the logging system but does not throw any exception.
   *
   * @param message is a message to be emitted
   */
  public static void trace(final String message, final Throwable t) {
    if (FenMatrix.logger != null) {
      FenMatrix.logger.trace(message, t);
    } else {
      System.err.printf("TRACE: %s : %s\n", message, t.getMessage());
      t.printStackTrace(System.err);
    }
  }

  /**
   * This method unconditionally emits a message to the logging system but does not throw any exception.
   *
   * @param message is a message to be emitted
   */
  public static void trace(final Throwable t) {
    if (FenMatrix.logger != null) {
      FenMatrix.logger.trace(t.getMessage(), t);
    } else {
      System.err.printf("TRACE: %s\n", t.getMessage());
      System.err.println(t.getMessage());
      t.printStackTrace(System.err);
    }
  }

  /**
   * This method to validate whether code is being run in
   * experimental mode or not
   */
  public static void validateExperimentalMode() {
    if (System.getProperty("EXPERIMENTAL") == null)
      throw new UnsupportedOperationException("Work in progress");
  }

}
