package com.android_mvc.nakisousou.controller;


import com.android_mvc.nakisousou.activities.func_db.DBEditActivity;
import com.android_mvc.nakisousou.controller.util.ValidationsUtil;
import com.android_mvc.framework.controller.validation.ValidationResult;

/**
 * DB操作系の画面のバリデーション処理の記述。
 * @author id:language_and_engineering
 *
 */
public class FuncDBValidation extends ValidationsUtil
{

    // Activityごとに引数の型を変えてオーバーロードする。

    /**
     * DB登録画面での入力値を検証
     */
    public ValidationResult validate(DBEditActivity activity)
    {
        initValidationOf(activity);

        assertNotEmpty("name");

        assertNotEmpty("nakisoucount");
        assertValidInteger("nakisoucount");
        assertNumberOperation("nakisoucount", greaterThan(0));

        return getValidationResult();
    }
}
