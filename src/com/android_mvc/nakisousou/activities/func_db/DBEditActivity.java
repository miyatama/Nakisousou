package com.android_mvc.nakisousou.activities.func_db;

import android.view.View;
import android.view.View.OnClickListener;

import com.android_mvc.nakisousou.controller.FuncDBController;
import com.android_mvc.framework.activities.base.BaseNormalActivity;
import com.android_mvc.framework.controller.validation.ActivityParams;
import com.android_mvc.framework.ui.UIBuilder;
import com.android_mvc.framework.ui.UIUtil;
import com.android_mvc.framework.ui.view.MButton;
import com.android_mvc.framework.ui.view.MCheckBox;
import com.android_mvc.framework.ui.view.MEditText;
import com.android_mvc.framework.ui.view.MLinearLayout;
import com.android_mvc.framework.ui.view.MTextView;

/**
 * サンプルのDB登録アクティビティ。
 * @author id:language_and_engineering
 *
 */
public class DBEditActivity extends BaseNormalActivity {

    MLinearLayout layout1;
    MTextView tv1;
    MEditText et1;

    MLinearLayout layout2;
    MTextView tv2;
    MEditText et2;

    MLinearLayout layout3;
    MTextView tv3;
    MCheckBox chbox1;

    MButton button1;


    @Override
    public void defineContentView() {

        final DBEditActivity activity = this;

        // 入力フォームUIを動的に構築する。
        new UIBuilder(context)
        .add(

            layout1 = new MLinearLayout(context)
              .orientationHorizontal()
              .widthFillParent()
              .add(

                tv1 = new MTextView(context)
                  .text("名前：" )
                  .widthWrapContent()
                ,

                et1 = new MEditText(context)
                  .widthPx(300)

              )
            ,

            layout2 = new MLinearLayout(context)
              .orientationHorizontal()
              .widthFillParent()
              .add(

                  tv2 = new MTextView(context)
                    .text("泣きそう数：" )
                    .widthWrapContent()
                  ,

                  et2 = new MEditText(context)
                    .widthPx(200)

              )

            ,

            button1 = new MButton(context)
              .text("この内容でDB登録")
              .click(new OnClickListener(){

                  @Override
                  public void onClick(View v) {
                      FuncDBController.submit(activity);
                  }

              })
        )
      .display();

    }


    @Override
    public void afterViewDefined()
    {
        if( $.intentHasKey("hoge") )
        {
            // Intentから受け取った値をToastで表示
            UIUtil.longToast(this, $.extras().getString("hoge") );
        }
    }


    @Override
    public ActivityParams toParams() {
        // 入力された値をすべて回収
        return new ActivityParams()
            .add("名前", "name", et1.text() )
            .add("泣きそう数", "nakisoucount", et2.text() )
        ;
    }

}