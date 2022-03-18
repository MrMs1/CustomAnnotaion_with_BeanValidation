# CustomAnnotaion_with_BeanValidation

## Overview
Bean Validationを用いたカスタムアノテーション作成メモ。  
今回作成したアノテーションは日付を行うアノテーション。

## バリデーション内容
- 値がnullのときはチェックしない
- 日付フォーマット(yyyy/MM/dd HH:mm:ss)に一致しているか正規表現でチェック
- 存在している日付かチェック
