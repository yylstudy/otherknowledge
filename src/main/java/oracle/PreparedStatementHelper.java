package oracle;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.HashMap;
import java.util.Iterator;

/**
 * <p>Title: pridigy</p>
 *
 * <p>Description: 当中文数据录入到数据库中不能显示汉字时, 用这个类来

 * 控制java.sql.PreparedStatement的setString(int i, String val)方法,
 * 在该方法中做字符转换.</p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: ztesoft</p>
 *
 * @author fan.zhenzhi
 * @version 1.0
 */
// ！！！！！！！！！！！public static void setParam(int index, java.sql.Date val, PreparedStatement ps)  此方法已废弃 谁再加回来的考核B！！！！！！！！！！！
//  public static void setParam(int index, java.sql.Date val, PreparedStatement ps,String dbType)  多加个类型参数 传getDbType() 进去
public final class PreparedStatementHelper {

    /** 默认编码集. */
    private static String DEFAULT_ENCODING = "GBK";

    /** 源编码集. */
    private static String FROM_ENCODING = "GBK";

    /** 要转换成的编码集. */
    private static String TO_ENCODING = "ISO-8859-1";

    /** 编码模式. */
    private static int ENCODE_MODE = 1;

    private static boolean ENCODE_FLAG = false;

    private static int SET_PARAM_STEP_LEN = 10;


    /**
     * 私有构造器
     */
    private PreparedStatementHelper() {
    }

    /**
     * 封装PreparedStatement的setString方法, 转换字符集.
     * @param index int 索引列 @see java.sql.PreparedStatement
     * @param value String 数据值 @see java.sql.PreparedStatement
     * @param ps PreparedStatement
     * @throws SQLException
     */
    public static void setString(int index, String value, PreparedStatement ps) throws SQLException {
        if (ps != null) {
            try {
                if (value != null) {

                    ps.setString(index, value);
                } else {
                    ps.setNull(index, Types.VARCHAR);
                }
            } catch (Exception e) {

            }
        } else {

        }
    }




    /**
     *
     * @param index int
     * @param ps PreparedStatement
     * @throws SQLException
     */
    public static void setNullLong(int index,
                                   PreparedStatement ps) throws SQLException {
        ps.setNull(index, Types.DOUBLE);
    }

    /**
     * 加工PreparedStatement的setDouble方法, 当value的值为NULL_DOUBLE时

     * 会调用setNull方法
     * @param index int 列索引

     * @param value double 插入值

     * @param ps PreparedStatement
     * @throws SQLException
     */
    public static void setDouble(int index, double value,
                                 PreparedStatement ps) throws SQLException {
        ps.setDouble(index, value);
    }

    /**
     * 调用setNull方法
     * @param index int
     * @param ps PreparedStatement
     * @throws SQLException
     */
    public static void setNullDouble(int index,
                                     PreparedStatement ps) throws SQLException {
        ps.setNull(index, Types.DOUBLE);
    }

    /**
     * 加工PreparedStatement的setFloat方法, 当value的值为NULL_FLOAT时

     * 会调用setNull方法
     * @param index int 列索引

     * @param value float 插入值

     * @param ps PreparedStatement
     * @throws SQLException
     */
    public static void setFloat(int index, float value,
                                PreparedStatement ps) throws SQLException {
        ps.setFloat(index, value);
    }


    /**
     *
     * @param index int
     * @param ps PreparedStatement
     * @throws SQLException
     */
    public static void setNullFloat(int index,
                                    PreparedStatement ps) throws SQLException {
        ps.setNull(index, Types.FLOAT);
    }

    /**
     * 加工PreparedStatement的setInt方法, 当value的值为NULL_INT时

     * 会调用setNull方法
     * @param index int 列索引

     * @param value int 插入值

     * @param ps PreparedStatement
     * @throws SQLException
     */
    public static void setInt(int index, int value,
                              PreparedStatement ps) throws SQLException {
        ps.setInt(index, value);
    }

    /**
     *
     * @param index int
     * @param ps PreparedStatement
     * @throws SQLException
     */
    public static void setNullInt(int index, PreparedStatement ps) throws SQLException {
        ps.setNull(index, Types.INTEGER);
    }

    /**
     * .
     * @param index int
     * @param ps PreparedStatement
     * @throws SQLException
     */
    public static void setNullNumber(int index, PreparedStatement ps) throws SQLException {
        ps.setNull(index, Types.NUMERIC);
    }

    public static void setNullString(int index, PreparedStatement ps) throws SQLException {
        ps.setNull(index, Types.VARCHAR);
    }


    /**
     *
     * @param index int
     * @param val int
     * @param ps PreparedStatement
     * @throws SQLException
     */
    public static void setParam(int index, int val, PreparedStatement ps) throws SQLException {
        setInt(index, val, ps);
    }

    public static void setParam(int index, float val, PreparedStatement ps) throws SQLException {
        setFloat(index, val, ps);
    }

    public static void setParam(int index, double val, PreparedStatement ps) throws SQLException {
        setDouble(index, val, ps);
    }

    public static void setParam(int index, long val, PreparedStatement ps) throws SQLException {
        setLong(index, val, ps);
    }
    public static void setLong(int index, long value,
                               PreparedStatement ps) throws SQLException {
        if (value == -9999999999999998L) {
            setNullLong(index, ps);
        } else {
            ps.setLong(index, value);
        }
    }

    public static void setParam(int index, Integer val, PreparedStatement ps) throws SQLException {
        if (val == null) {
            setNullInt(index, ps);
        } else {
            setInt(index, val.intValue(), ps);
        }
    }

    public static void setParam(int index, Long val, PreparedStatement ps) throws SQLException {
        if (val == null) {
            setNullLong(index, ps);
        } else {
            setLong(index, val.longValue(), ps);
        }
    }

    public static void setParam(int index, Float val, PreparedStatement ps) throws SQLException {
        if (val == null) {
            setNullFloat(index, ps);
        } else {
            setFloat(index, val.intValue(), ps);
        }
    }

   public static void setParam(int index, Double val, PreparedStatement ps) throws SQLException {
        if (val == null) {
            setNullDouble(index, ps);
        } else {
            setDouble(index, val.doubleValue(), ps);
        }
    }

    public static void setParam(int index, String val, PreparedStatement ps) throws SQLException {
        if (val == null) {
            setNullString(index, ps);
        } else {
            setString(index, val, ps);
        }
    }

    public static void setParam(int index, String val, PreparedStatement ps,String dbType) throws SQLException {
        if (val == null) {
            setNullString(index, ps);
        } else {
            setString(index, val, ps,dbType);
        }
    }
    /**
     * 封装PreparedStatement的setString方法, 转换字符集.
     * @param index int 索引列 @see java.sql.PreparedStatement
     * @param value String 数据值 @see java.sql.PreparedStatement
     * @param ps PreparedStatement
     * @throws SQLException
     */
    public static void setString(int index, String value, PreparedStatement ps,String dbType) throws SQLException {
        if (ps != null) {
            try {
                if (value != null) {
                    ps.setString(index, value);
                } else {
                    ps.setNull(index, java.sql.Types.VARCHAR);
                }
            } catch (Exception e) {
            }
        } else {
        }
    }


    public static void setParam(int index, Timestamp val, PreparedStatement ps) throws
            SQLException {
        if (val == null) {
            ps.setNull(index, Types.TIMESTAMP);
        } else {
            ps.setTimestamp(index, val);
        }
    }

    /** 左括号 */
    private static String LEFT_BRACKET = " ( ";
    /** 右括号 */
    private static String RIGHT_BRACKET = " ) ";
    /** 问号 */
    private static String INTERROGATION = " ? ";
    /** 逗号 */
    private static String COMMA = " , ";

    /**
     * get ps字符串

     * @param sb StringBuffer
     * @param listArr String[]
     */
    public static void getPsString(StringBuffer sb, String[] listArr) {
        sb.append(LEFT_BRACKET);
        for (int i = 0; i < listArr.length; i++) {
            sb.append(INTERROGATION);
            if (i != listArr.length - 1) {
                sb.append(COMMA);
            }
        }
        sb.append(RIGHT_BRACKET);
    }
    /**
     * get ps字符串

     * @param sb StringBuffer
     * @param listArr String[]
     */
    public static void getPsLong(StringBuffer sb, long[] listArr) {
        sb.append(LEFT_BRACKET);
        for (int i = 0; i < listArr.length; i++) {
            sb.append(INTERROGATION);
            if (i != listArr.length - 1) {
                sb.append(COMMA);
            }
        }
        sb.append(RIGHT_BRACKET);
    }

    /**
     * get ps字符串

     * @param sb StringBuffer
     * @param listArr String[]
     */
    public static void getPsLong(StringBuffer sb, Long[] listArr) {
        sb.append(LEFT_BRACKET);
        for (int i = 0; i < listArr.length; i++) {
            sb.append(INTERROGATION);
            if (i != listArr.length - 1) {
                sb.append(COMMA);
            }
        }
        sb.append(RIGHT_BRACKET);
    }

    /**
     * get ps字符串

     * @param sb StringBuffer
     * @param hm String[]
     */
    public static void getPsString(StringBuffer sb, HashMap hm) {
        sb.append(LEFT_BRACKET);
        for (int i = 0; i < hm.size(); i++) {
        	if(i > 0) {
        		sb.append(COMMA);
        	}
            sb.append(INTERROGATION);
        }

        if (hm.size() % SET_PARAM_STEP_LEN != 0) { // 设置个数未达到10的倍数，补足到10的倍数
			int lastedCount = SET_PARAM_STEP_LEN - (hm.size() % SET_PARAM_STEP_LEN);
			for (int j = 0; j < lastedCount; j++) {
				if (sb.length() > 0) {
					sb.append(COMMA);
				}
				sb.append(INTERROGATION);
			}
		}
        sb.append(RIGHT_BRACKET);
    }

    /**
     * get ps字符串

     * @param sb StringBuffer
     * @param it Iterator
     */
    public static void getPsString(StringBuffer sb, Iterator it, int maxSize) {
    	int hasCount = 0; //已设置个数

		int lastedCount = 0; //到达10的倍数差多少


        sb.append(LEFT_BRACKET);
        for (int i = 0; i < maxSize; i++) {
        	if(it.hasNext()) {
        		if (i > 0) {
        			sb.append(COMMA);
        		}
        		sb.append(INTERROGATION);
        		it.next();
        		hasCount++;
        	} else {
        		if(hasCount % SET_PARAM_STEP_LEN != 0) { //设置个数未达到10的倍数，补足到10的倍数
					lastedCount = SET_PARAM_STEP_LEN - (hasCount % SET_PARAM_STEP_LEN) ;
					for(int j = 0; j < lastedCount; j++) {
						if (i > 0) {
		        			sb.append(COMMA);
		        		}
		        		sb.append(INTERROGATION);
					}
				}
        		break;
        	}
        }
        sb.append(RIGHT_BRACKET);
    }
    

    /**
     * get ps字符串

     * @param sb StringBuffer
     * @param strArr String[]
     * @param setParamStepLen strArr的长度若不足该数的倍数，则补齐到该数的倍数
     */
    public static void getPsString(StringBuffer sb, String[] strArr, int setParamStepLen) {
    	sb.append(LEFT_BRACKET);
        for (int i = 0; i < strArr.length; i++) {
        	if(i > 0) {
        		sb.append(COMMA);
        	}
            sb.append(INTERROGATION);
        }

        int modValue = strArr.length % setParamStepLen;
        if (modValue != 0) { // 设置个数未达到倍数，补足到倍数
			int lastedCount = setParamStepLen - modValue;
			for (int j = 0; j < lastedCount; j++) {
				if (sb.length() > 0) {
					sb.append(COMMA);
				}
				sb.append(INTERROGATION);
			}
		}
        sb.append(RIGHT_BRACKET);
    }
    /**
     * get ps字符串

     * @param sb StringBuffer
     * @param strArr String[]
     * @param setParamStepLen strArr的长度若不足该数的倍数，则补齐到该数的倍数
     */
    public static void getPsString(StringBuffer sb, int[] strArr, int setParamStepLen) {
    	sb.append(LEFT_BRACKET);
        for (int i = 0; i < strArr.length; i++) {
        	if(i > 0) {
        		sb.append(COMMA);
        	}
            sb.append(INTERROGATION);
        }

        int modValue = strArr.length % setParamStepLen;
        if (modValue != 0) { // 设置个数未达到倍数，补足到倍数
			int lastedCount = setParamStepLen - modValue;
			for (int j = 0; j < lastedCount; j++) {
				if (sb.length() > 0) {
					sb.append(COMMA);
				}
				sb.append(INTERROGATION);
			}
		}
        sb.append(RIGHT_BRACKET);
    }

    /**
     * get ps字符串

     * @param sb StringBuffer
     * @param listArr String[]
     */
    public static void getPsString(StringBuffer sb, int[] listArr) {
        sb.append(LEFT_BRACKET);
        for (int i = 0; i < listArr.length; i++) {
            sb.append(INTERROGATION);
            if (i != listArr.length - 1) {
                sb.append(COMMA);
            }
        }
        sb.append(RIGHT_BRACKET);
    }
   

    /**
     * set ps字符串

     * @param ps PreparedStatement
     * @param dbloop int
     * @param listArr String[]
     * @return int
     * @throws SQLException
     */
    public static int setPsString(PreparedStatement ps, int dbloop, String[] listArr) throws
            SQLException {
        for (int i = 0; i < listArr.length; i++) {
            ps.setString(dbloop++, listArr[i]);
        }
        return dbloop;
    }
    




    /**
     * set ps字符串

     * @param ps PreparedStatement
     * @param dbloop int
     * @param strArr String[]
     * @param setPramStepLen strArr的长度若不足该数的倍数，则补齐到该数的倍数
     * @return int
     * @throws SQLException
     */
    public static int setPsString(PreparedStatement ps, int dbloop, String[] strArr, int setPramStepLen) throws
            SQLException {
    	String lastValue = null;
    	int size = strArr.length;
		for (int i = 0; i < size; i++) {
			lastValue = strArr[i];
			ps.setString(dbloop++, lastValue);
		}

		int modValue = size % setPramStepLen;
		if (modValue != 0) { // 设置个数未达到10的倍数，补足到10的倍数
			int lastedCount = setPramStepLen - modValue;
			for (int j = 0; j < lastedCount; j++) {
				ps.setString(dbloop++, lastValue);
			}
		}

		return dbloop;
    }

    /**
     * set Ps As Int
     * @param ps
     * @param dbloop
     * @param hm
     * @return
     * @throws SQLException
     */
    public static int setPsInt(PreparedStatement ps, int dbloop, HashMap hm) throws SQLException {
		int lastValue = 0;
    	int size = hm.size();
    	Iterator it = hm.keySet().iterator();
		for (int i = 0; i < size; i++) {
			lastValue = ((Integer) it.next()).intValue();
			ps.setInt(dbloop++, lastValue);
		}

		if (size % SET_PARAM_STEP_LEN != 0) { // 设置个数未达到10的倍数，补足到10的倍数
			int lastedCount = SET_PARAM_STEP_LEN - (size % SET_PARAM_STEP_LEN);
			for (int j = 0; j < lastedCount; j++) {
				ps.setInt(dbloop++, lastValue);
			}
		}

		return dbloop;
	}
   
    /**
     * set Ps As Int
     * @param ps
     * @param dbloop
     * @param it
     * @return
     * @throws SQLException
     */
    public static int setPsInt(PreparedStatement ps, int dbloop, Iterator it, int maxSize) throws SQLException {
		int hasCount = 0; //已设置个数

		int lastedCount = 0; //到达10的倍数差多少

		int lastValue = 0; //上一次设置的值


    	for (int i = 0; i < maxSize; i++) {
			if(it.hasNext()) {
				lastValue = ((Integer) it.next()).intValue();
				ps.setInt(dbloop++, lastValue);
				hasCount++;
			} else {
				if(hasCount % SET_PARAM_STEP_LEN != 0) { //设置个数未达到10的倍数，补足到10的倍数
					lastedCount = SET_PARAM_STEP_LEN - (hasCount % SET_PARAM_STEP_LEN);
					for(int j = 0; j < lastedCount; j++) {
						ps.setInt(dbloop++, lastValue);
					}
				}
				break;
			}
		}
		return dbloop;
	}
    

    /**
     * set Ps As Int
     * @param ps
     * @param dbloop
     * @param listArr
     * @return
     * @throws SQLException
     */
    public static int setPsInt(PreparedStatement ps, int dbloop, int[] listArr) throws SQLException {
    	for (int i = 0; i < listArr.length; i++) {
            ps.setInt(dbloop++, listArr[i]);
        }
        return dbloop;
	}
    
    /**
     * set ps字符串

     * @param ps PreparedStatement
     * @param dbloop int
     * @param valArr int[]
     * @param setPramStepLen strArr的长度若不足该数的倍数，则补齐到该数的倍数
     * @return int
     * @throws SQLException
     */
    public static int setPsStringArr(PreparedStatement ps, int dbloop, String[] valArr, int setPramStepLen) throws
            SQLException {
    	String lastValue = null;
    	int size = valArr.length;
		for (int i = 0; i < size; i++) {
			lastValue = valArr[i];
			ps.setString(dbloop++, lastValue);
		}

		int modValue = size % setPramStepLen;
		if (modValue != 0) { // 设置个数未达到倍数，补足到倍数
			int lastedCount = setPramStepLen - modValue;
			for (int j = 0; j < lastedCount; j++) {
				ps.setString(dbloop++, lastValue);
			}
		}

		return dbloop;
    }
    /**
     * 得到Oracle的空字符串

     * @param input String
     * @return String
     */
    public static String getOracleNullString(String input) {
        if (input == null) {
            return input;
        } else if (input.length() == 0) {
            return " ";
        } else {
            return input;
        }
    }
    

    // ！！！！！！！！！！！public static void setParam(int index, java.sql.Date val, PreparedStatement ps)  此方法已废弃 谁再加回来的考核B！！！！！！！！！！！
}

