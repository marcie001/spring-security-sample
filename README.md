# Spring Security Sample

## 起動

PostgreSQL が起動していれば、テーブルやデータは起動時に作成します。

    $ ./mvnw spring-boot:run

## 確認方法

1. ブラウザで http://127.0.0.1:8080/ にアクセスする。
1. ログインする
    1. username: admin, password: password - ADMIN 権限ユーザ
    1. username: user01, password: password - USER 権限ユーザ
    1. username: user02, password: password - USER 権限ユーザ
1. ADMIN 権限のユーザでログインすると ADMIN 用ページへのリンクが表示される
1. 記事一覧にアクセスすると、 ログインユーザ の ID が `Article#userId` と同じ記事にのみリンクが表示される。
