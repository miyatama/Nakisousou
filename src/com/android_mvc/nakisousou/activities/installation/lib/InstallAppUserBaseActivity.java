package com.android_mvc.nakisousou.activities.installation.lib;

import android.database.sqlite.SQLiteDatabase;

import com.android_mvc.nakisousou.common.AppSettings;
import com.android_mvc.nakisousou.db.schema.SchemaDefinition;
import com.android_mvc.framework.activities.installation.InstallAppFWBaseActivity;
import com.android_mvc.framework.common.FWUtil;
import com.android_mvc.framework.db.schema.RDBSchema;

/**
 * ユーザがいじる必要のない初期化処理ロジック。
 * APとFWの橋渡しをするため，FW側に置けない部分。
 * @author id:language_and_engineering
 *
 */
public abstract class InstallAppUserBaseActivity extends InstallAppFWBaseActivity
{

    // NOTE:このクラスをActivityではなくしてロジッククラスにしてしまうと，
    // installアクティビティからロジッククラスを必ず呼び出すように規約が生じてしまう。
    // ユーザのコード量を減らしつつ，なおかつFW側に置けないコードなのでこうなる。

    @Override
    protected void injectAppInfoIntoFW()
    {
        // AP側の設定を，FW側で受理させる。
        receiveAppInfoAsFW( new AppSettings(this) );
            // NOTE: AppSettingを参照するので，このコードはFW側に置けない。
            // NOTE: これはパッケージをまたいだ参照渡しの処理だが，継承階層内で行われる。
            // したがって，本Activityは，APの顔とFWの顔という二面性を持つことになる。邪道だが面白い。

    }


    @Override
    protected void init_db_schema()
    {
        // DBとテーブルを作成
        new RDBSchema( this ).createIfNotExists( new SchemaDefinition() );
    }


    @Override
    protected void init_db_data(SQLiteDatabase db)
    {
        FWUtil.d("初期データを投入します。");
        new SchemaDefinition().init_db_data(db, this);
    }

}
