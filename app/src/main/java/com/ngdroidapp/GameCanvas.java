package com.ngdroidapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import istanbul.gamelab.ngdroid.base.BaseCanvas;
import istanbul.gamelab.ngdroid.util.Log;
import istanbul.gamelab.ngdroid.util.Utils;


/**
 * Created by noyan on 24.06.2016.
 * Nitra Games Ltd.
 * Edited by Muhammed T. Karakul on 15.06.2018
 */


public class GameCanvas extends BaseCanvas {

    // Birtakim sabitler tanimlaniyor.
    private int centerX = getWidth() / 2;
    private int centerY = getHeight() / 2;
    private int resizer = 4;


    // Ekrana basilacak logo ile ilgili degiskenler tanimlaniyor.
    private Bitmap logo;
    private int logox, logoy, logow, logoh;

    // Zemin kaplamasi(tile) ile ilgili degiskenler tanimlaniyor.
    private Bitmap tileSet;
    private Rect tileDestination, tileSource;
    private int tileSourceX, tileSourceY, tileSourceWidth, tileSourceHeight;
    private int tileDestinationX, tileDestinationY, tileDestinationWidth, tileDestinationHeight;

    // Sprite ile ilgili degiskenler tanimlaniyor.
    private Bitmap spriteSheet;
    private Rect spriteSource, spriteDestination;
    private int spriteSourceX, spriteSourceY, spriteSourceWidth, spriteSourceHeight;
    private int spriteDestinationX, spriteDestinationY, spriteDestinationWidth, spriteDestinationHeight;
    private int spriteVelocityX, spriteVelocityY, spriteIndicatorX, spriteIndicatorY;
    private int direction;

    // Animasyonlar ile ilgili değişkenler tanımlanıyor.
    private int animationTypes = 4;
    private int frameNum, animationType, animationFirstFrameNum[], animationLastFrameNum[];
    private int shotControl;

    // Ekranda dokunulan noktanın koordinatlarını tutması için değişkenler tenımlıyoruz.
    private int touchDownX, touchDownY;

    // Kaya nesnesi ile ilgili değişkenleri tanımlıyoruz.
    private Bitmap rockImage;
    private Rect rockSource, rockDestination;
    private int rockSourceX, rockSourceY, rockSourceWidth, rockSourceHeight;
    private int rockDestinationX, rockDestinationY, rockDestinationWidth, rockDestinationHeight;

    // Button nesnesi ile ilgili değişkenleri tanımlıyoruz.
    private Bitmap buttonImage;
    private Rect buttonSource, buttonDestination;
    private int buttonSourceX, buttonSourceY, buttonSourceWidth, buttonSourceHeight;
    private int buttonDestinationX, buttonDestinationY, buttonDestinationWidth, buttonDestinationHeight;

    // Bullet nesnesi ile ilgili değişkenleri tanımlıyoruz.
    private Bitmap bulletImage;
    private Rect bulletSource, bulletDestination;
    private int bulletSourceX, bulletSourceY, bulletSourceWidth, bulletSourceHeight;
    private int bulletDestinationX, bulletDestinationY, bulletDestinationWidth, bulletDestinationHeight;
    private boolean bulletState;
    private int bulletDirectionX, bulletDirectionY;
    private int bulletVelocity;

    // Düşman ile ilgili değişkenler tanımlanıyor.
    private Bitmap enemyImage;
    private Rect enemySource, enemyDestination;
    private int enemySourceX, enemySourceY, enemySourceWidth, enemySourceHeight;
    private int enemyDestinationX, enemyDestinationY, enemyDestinationWidth, enemyDestinationHeight;
    private boolean enemyState;
    private boolean enemyDirection;

    public GameCanvas(NgApp ngApp) {
        super(ngApp);
    }

    public void setup() {
        //Log.i(TAG, "setup");
        //setupLogo("gamelab-istanbul_logo.png");
        //setupTileSet("tile.png");
        setupTile("tile.png");
        setupSprite("cowboy.png");
        setupAnimation();
        setupRock("rock01.png");
        setupButton("button.png");
        shotControl = 0;
        setupBullet("roundBullet.png");
        setupEnemy("enemy.png");
    }

    private void setupEnemy(String imagePath) {
        enemyImage = Utils.loadImage(root, imagePath);
        setupEnemySource(0,0, 263, 202);
        setupEnemyDestination(((getWidth() - 256) / 2), (getHeight() - 500), 256, 256);
        enemyState = true;
        enemyDirection = true;
    }

    private void setupEnemySource(int x, int y, int width, int height) {
        enemySourceX = x;
        enemySourceY = y;
        enemySourceWidth = width;
        enemySourceHeight = height;
        enemySource = new Rect();
    }

    private void setupEnemyDestination(int x, int y, int width, int height) {
        enemyDestinationX = x;
        enemyDestinationY = y;
        enemyDestinationWidth = width;
        enemyDestinationHeight = height;
        enemyDestination = new Rect();
    }

    /**
     *
     * @param imagePath
     */
    private void setupBullet(String imagePath) {
        bulletImage = Utils.loadImage(root, imagePath);
        setupBulletSource(0,0, 300, 300);
        setupBulletDestination(0, 0, 30, 30);
        bulletState = false;
        bulletVelocity = 100;
        bulletDirectionX = spriteIndicatorX;
        bulletDirectionY = spriteIndicatorY;
    }

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    private void setupBulletSource(int x, int y, int width, int height) {
        bulletSourceX = x;
        bulletSourceY = y;
        bulletSourceWidth = width;
        bulletSourceHeight = height;
        bulletSource = new Rect();
    }

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    private void setupBulletDestination(int x, int y, int width, int height) {
        bulletDestinationX = x;
        bulletDestinationY = y;
        bulletDestinationWidth = width;
        bulletDestinationHeight = height;
        bulletDestination = new Rect();
    }

    /**
     *
     * @param imagePath
     */
    private void setupButton(String imagePath) {
        buttonImage = Utils.loadImage(root, imagePath);
        setupButtonSource(0,0,1190,1190);
        setupButtonDestination((getWidth() - 240), (getHeight() - 240), 240, 240);
    }

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    private void setupButtonSource(int x, int y, int width, int height) {
        buttonSourceX = x;
        buttonSourceY = y;
        buttonSourceWidth = width;
        buttonSourceHeight = height;
        buttonSource = new Rect();
    }

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    private void setupButtonDestination(int x, int y, int width, int height) {
        buttonDestinationX = x;
        buttonDestinationY = y;
        buttonDestinationWidth = width;
        buttonDestinationHeight = height;
        buttonDestination = new Rect();
    }

    /**
     * Kaya nesnesi ile ilgili değişkenlere ilk değer atamalarını yapar.
     */
    private void setupRock(String imagePath) {
        rockImage = Utils.loadImage(root, imagePath);

        setupRockSource(0,0,382,279);
        setupRockDestination(centerX - 128, centerY - 128, 256,256);
    }

    /**
     * Kaya nesnesinin görselinin çekileceği dosyadan alınacak alanın koordinatları ve boyut bilgileri değişkenlere atanıyor.
     * @param x Kırpılacak alanın sol üst x koordinatı.
     * @param y Kırpılacak alanın sol üst y koordinatı.
     * @param width Kırpılacak alanın genişliği.
     * @param height Kırpılacak alanın yüksekliği.
     */
    private void setupRockSource(int x, int y, int width, int height) {
        rockSourceX = x; // 0
        rockSourceY = y; // 0
        rockSourceWidth = width; // 382
        rockSourceHeight = height; // 279
        rockSource = new Rect();
        rockSource.set(rockSourceX, rockSourceY, (rockSourceX + rockSourceWidth), (rockSourceY + rockSourceHeight));
    }

    /**
     * Kaya nesnesinin ekranda basılacağı alanın koordinat ve boyut bilgileri değişkenlere atanıyor.
     * @param x Nesnenin ekrana basılacağı alanın sol üst x koordinatı.
     * @param y Nesnenin ekrana basılacağı alanın sol üst y koordinatı.
     * @param width Nesnenin ekrana basılacağı alanın genişliği.
     * @param height Nesnenin ekrana basılacağı alanın yüksekliği.
     */
    private void setupRockDestination(int x, int y, int width, int height) {
        rockDestinationWidth = width; // 256
        rockDestinationHeight = height; // 256
        rockDestinationX = x; // (getWidth() - rockDestinationWidth) / 2
        rockDestinationY = y; // (getHeight() - rockDestinationHeight) / 2
        rockDestination = new Rect();
        rockDestination.set(rockDestinationX, rockDestinationY, (rockDestinationX + rockDestinationWidth), (rockDestinationY + rockDestinationHeight));
    }

    /**
     * Animasyonlar ile ilgili değişkenlere ilk değer atamaları yapılıyor.
     * Animasyonların hangi frame de başlayıp hangi frame de biteceği gibi değerleri tutan değişkenlere
     * ilk değer atamaları yapılıyor.
     */
    private void setupAnimation() {
        frameNum = 0; // cowboy.png'de soldan kaçıncı karede olduğumuzu gösterir.
        animationType = 1; // Animasyon türleri: durma = 0, yürüme = 1, silah doğrultma = 2, ateş etme = 3

        animationFirstFrameNum = new int[animationTypes]; // Animasyon türü kadar animasyon başlangıç karesi yeri ayrılıyor.
        animationLastFrameNum = new int[animationTypes]; // Animasyon türü kadar animasyon bitiş karesi yeri ayrılıyor.

        // Durma animasyonu için başlangıç ve bitiş kare numaraları belirleniyor.
        animationFirstFrameNum[0] = 0;
        animationLastFrameNum[0] = 0;

        // Yürüme animasyonu için başlangıç ve bitiş kare numaraları belirleniyor.
        animationFirstFrameNum[1] = 1;
        animationLastFrameNum[1] = 8;

        // Silah çekme animasyonu için başlangıç ve bitiş kare numaraları belirleniyor.
        animationFirstFrameNum[2] = 9;
        animationLastFrameNum[2] = 11;

        // Ateş etme animasyonu için başlangıç ve bitiş kare numaraları belirleniyor.
        animationFirstFrameNum[3] = 12;
        animationLastFrameNum[3] = 13;
    }

    /**
     * Bu metod logo ile ilgili degiskenlerin ilk deger atamalarini yapar.
     * @param imagePath Görselinin çekileceği dosyanın yolu.
     */
    private void setupLogo(String imagePath) {
        logo = Utils.loadImage(root,imagePath);
        logow = logo.getWidth();
        logoh = logo.getHeight();
        logox = (getWidth() - logow) / 2;
        logoy = (getHeight() - logoh) / 2;
    }

    /**
     * Bu metod zemin kaplamasi seti ile ilgili degiskenlerin ilk deger atamalarini yapar.
     * @param imagePath Görselinin çekileceği dosyanın yolu.
     */
    private void setupTileSet(String imagePath) {
        tileSet = Utils.loadImage(root, imagePath);
        tileDestinationWidth = tileSet.getWidth();
        tileDestinationHeight = tileSet.getHeight();
        tileDestinationX = (getWidth() - tileDestinationWidth) / 2;
        tileDestinationY = (getHeight() - tileDestinationHeight) / 2;
    }

    /**
     * Bu metod zemin kaplamasinin hedef ve kaynakla ilgili ayarlamalari yapan metodlari cagirir ve
     * zemin kaplama seti dosyasini cekme islemini gerceklestirir.
     * @param imagePath Görselinin çekileceği dosyanın yolu.
     */
    private void setupTile(String imagePath) {
        tileSet = Utils.loadImage(root, imagePath);
        setupTileSource(0,0,64,64);
        setupTileDestination(0 ,0 ,64,64);
    }

    /**
     * Bu metod zemin kaplamasi alinacagi kaynak ile ilgili degiskenlere ilk deger atamalarini yapar.
     */
    private void setupTileSource(int x, int y, int width, int height) {
        tileSource = new Rect();
        tileSourceX = x;
        tileSourceY = y;
        tileSourceWidth = width;
        tileSourceHeight = height;
        tileSource.set(tileSourceX, tileSourceY, (tileSourceX + tileSourceWidth), (tileSourceY + tileSourceHeight));
    }

    /**
     * Bu metod zemin kaplamasi basilacagi hedef ile ilgili degiskenlere ilk deger atamalarini yapar.
     */
    private void setupTileDestination(int x, int y, int width, int height) {
        tileDestination = new Rect();
        tileDestinationX = x;
        tileDestinationY = y;
        tileDestinationWidth = width;
        tileDestinationHeight = height;
        tileDestination.set(tileDestinationX, tileDestinationY, (tileDestinationX + tileDestinationWidth), ( tileDestinationY + tileDestinationHeight));
    }

    /**
     * Bu metod sprite ile ilgili degiskenlere ilk deger atamalarini yapar.
     * @param imagePath Görselinin çekileceği dosyanın yolu.
     */
    private void setupSprite(String imagePath) {
        spriteSheet = Utils.loadImage(root, imagePath);
        setupSpriteSource(0,0,128,128);
        setupSpriteDestination(0,0,256,256);
        spriteVelocityX = spriteDestinationWidth / 8;
        spriteVelocityY = spriteDestinationHeight / 8;
        spriteIndicatorX = 1;
        spriteIndicatorY = 0;
        direction = 3;
    }

    /**
     * Bu metod sprite gorsellerinin cekilecegi dosyada belirli bir alani kirpip almaya yarar.
     * @param x
     * @param y
     * @param width
     * @param height
     */
    private void setupSpriteSource(int x, int y, int width, int height) {
        spriteSource = new Rect();
        spriteSourceX = x;
        spriteSourceY = y;
        spriteSourceWidth = width;
        spriteSourceHeight = height;
        spriteSource.set(spriteSourceX, spriteSourceY, spriteSourceX + spriteSourceWidth, spriteSourceY + spriteSourceHeight);
    }

    /**
     * Bu metod sprite gorselinin ekranda basilacagi alanin koordinat ve boyut bilgilerini belirler.
     * @param x
     * @param y
     * @param width
     * @param height
     */
    private void setupSpriteDestination(int x, int y, int width, int height) {
        spriteDestination = new Rect();
        spriteDestinationX = x;
        spriteDestinationY = y;
        spriteDestinationWidth = width;
        spriteDestinationHeight = height;
        spriteDestination.set(spriteDestinationX, spriteDestinationY, spriteDestinationX + spriteDestinationWidth, spriteDestinationY + spriteDestinationHeight);
    }

    public void update() {
        //Log.i(TAG, "update");
        //edgeControl();
        rockCollisionControl();
        updateAnimation();
        if(bulletState) {
            updateBulletPosition();
        }
        if(enemyState) {
            checkBulletEnemyCollision();
        }

        updateEnemyPosition();
    }

    private void updateEnemyPosition() {

        if(enemyDirection) {
            enemyDestinationX += 25;
        } else {
            enemyDestinationX -= 25;
        }

        if(enemyDestinationX > (getWidth() - enemyDestinationWidth) || enemyDestinationX < 0) {
            //enemyDestinationX += 25;
            enemyDirection = !enemyDirection;
        }
    }

    private void checkBulletEnemyCollision() {
        if(checkCollide(enemyDestination, bulletDestination)) {
            Log.i(TAG, "Düşman vuruldu.");
            enemyState = false;
            bulletState = false;
        }
    }

    private void updateBulletPosition() {
        if(bulletDestinationX <= (getWidth() - bulletDestinationWidth) && bulletDestinationY <= (getHeight() - bulletDestinationHeight) && bulletDestinationX >= 0 && bulletDestinationY >= 0) {
            bulletDestinationX += bulletVelocity * bulletDirectionX;
            bulletDestinationY += bulletVelocity * bulletDirectionY;
        } else {
            Log.i(TAG, "Mermi duvara çarptı.");
            bulletState = false;
        }

    }


    private void rockCollisionControl() {
        if(checkCollide(spriteDestination, rockDestination)) {
            //Log.i(TAG, "spriteVelocityX: " + spriteVelocityX + ", spriteVelocityY: " + spriteVelocityY + ", spriteIndicatorX: " + spriteIndicatorX + ", spriteIndicatorY: " + spriteIndicatorY);
            spriteDestinationX -= spriteVelocityX * spriteIndicatorX;
            spriteDestinationY -= spriteVelocityY * spriteIndicatorY;
            spriteIndicatorX = 0;
            spriteIndicatorY = 0;
            animationType = 0;
        }
    }

    private void updateAnimation() {
        // Update fonksiyonu her çalıştığında animasyon bir sonraki kareye geçer.
        frameNum++;

        // Animasyon son karesine ulaştığında framenum'u animasyonun ilk karesine getir.
        if(frameNum > animationLastFrameNum[animationType]) {
            frameNum = animationFirstFrameNum[animationType];
            if(shotControl > 0){
                shotControl = 0;
                animationType = 0;
            }
        }

        spriteDestinationX += spriteVelocityX * spriteIndicatorX;
        spriteDestinationY += spriteVelocityY * spriteIndicatorY;

        edgeControl();

        // cowboy.png'deki animasyon karesinin koordinatını framenum ve genişlik cinsinde girelim.
        spriteSourceX = frameNum * spriteSourceWidth;
        spriteSourceY = direction * spriteSourceHeight;
    }

    /**
     * Bu metod, sprite ekranın sağ tarafına yaklaşınca durmasını sağlar. Bu sayede sprite ekranın
     * dışına çıkmamış ve görüş alanımız içinde kalmış olur.
     */
    private void edgeControl() {
        if(spriteDestinationX > getWidth() - spriteDestinationWidth) {
            spriteDestinationX = getWidth() - spriteDestinationWidth;
            animationType = 0;
        } else if(spriteDestinationX < 0) {
            spriteDestinationX = 0;
            animationType = 0;
        } else if(spriteDestinationY > getHeight() - spriteDestinationHeight) {
            spriteDestinationY = getHeight() - spriteDestinationHeight;
            animationType = 0;
        } else if(spriteDestinationY < 0) {
            spriteDestinationY = 0;
            animationType = 0;
        }
    }

    public void draw(Canvas canvas) {
        //Log.i(TAG, "draw");
        //drawLogo(canvas);
        //drawTileSet(canvas);
        //drawTile(canvas);
        drawTileAllOver(canvas);
        drawSprite(canvas);
        drawRock(canvas);
        drawButton(canvas);
        if(bulletState) {
            drawBullet(canvas);
        }

        if(enemyState) {
            drawEnemy(canvas);
        }

    }

    private void drawEnemy(Canvas canvas) {
        enemySource.set(enemySourceX, enemySourceY, enemySourceWidth, enemySourceHeight);
        enemyDestination.set(enemyDestinationX, enemyDestinationY, (enemyDestinationX + enemyDestinationWidth), (enemyDestinationY + enemyDestinationHeight));
        canvas.drawBitmap(enemyImage, enemySource, enemyDestination, null);
    }

    /**
     *
     * @param canvas
     */
    private void drawBullet(Canvas canvas) {
        bulletSource.set(bulletSourceX, bulletSourceY, bulletSourceWidth, bulletSourceHeight);
        bulletDestination.set(bulletDestinationX, bulletDestinationY, (bulletDestinationX + bulletDestinationWidth), (bulletDestinationY + bulletDestinationHeight));
        canvas.drawBitmap(bulletImage, bulletSource, bulletDestination, null);
    }

    /**
     * Ekranın sağ alt köşesine buton nesnesini çizer.
     * @param canvas Butonun, üstüne çizileceği canvas nesnesi.
     */
    private void drawButton(Canvas canvas) {
        buttonSource.set(buttonSourceX, buttonSourceY, (buttonSourceX + buttonSourceWidth), (buttonSourceY + buttonSourceHeight));
        buttonDestination.set(buttonDestinationX, buttonDestinationY, (buttonDestinationX + buttonDestinationWidth), (buttonDestinationY + buttonDestinationHeight));
        canvas.drawBitmap(buttonImage, buttonSource, buttonDestination, null);
    }

    /**
     * Ekrana kaya objesini çizer.
     * @param canvas Kaya objesinin çizileceği canvas nesnesi.
     */
    private void drawRock(Canvas canvas) {
        canvas.drawBitmap(rockImage, rockSource, rockDestination, null);
    }

    /**
     * Bu metod ekrana logo'yu basar.
     * @param canvas Logonun, ustune cizilecegi canvas nesnesi.
     */
    private void drawLogo(Canvas canvas) {
        canvas.drawBitmap(logo, logox, logoy, null);
    }

    /**
     * Bu metot ekrana zemin kaplamasini setinden (tile set) basar.
     * @param canvas Zemin kaplamasinin, ustune cizilecegi canvas nesnesi.
     */
    private void drawTileSet(Canvas canvas) {
        canvas.drawBitmap(tileSet, tileDestinationX, tileDestinationY, null);
    }

    /**
     * Bu metod ekrana zemin kaplamasi setinden bir parcayi (tile) basar.
     * @param canvas Zemin kaplamasinin, ustune cizilecegi canvas nesnesi.
     */
    private void drawTile(Canvas canvas) {
        canvas.drawBitmap(tileSet, tileSource, tileDestination, null);
    }

    /**
     * Bu metod ekrani zemin kaplamasi ile doldurur.
     * @param canvas Zemin kaplamasinin, ustune cizilecegi canvas nesnesi.
     */
    private void drawTileAllOver(Canvas canvas) {
        for(int i = 0; i < getWidth(); i += tileDestinationWidth) {
            for (int j = 0; j < getHeight(); j += tileDestinationHeight) {
                setupTileSource(0, 0, 64, 64);
                setupTileDestination(i, j, 64 * resizer, 64 * resizer);
                drawTile(canvas);
            }
        }
    }

    /**
     * Sprite'ın hangi görselinin dosyadan çekileceğini ve ekranda hangi konuma basılacağını belirleyip çizme işlemini gerçekleştirir.
     * @param canvas Sprite'ın çizileceği kanvas nesnesi.
     */
    private void drawSprite(Canvas canvas) {
        spriteSource.set(spriteSourceX,spriteSourceY, spriteSourceX + spriteSourceWidth, spriteSourceY + spriteSourceHeight);
        spriteDestination.set(spriteDestinationX, spriteDestinationY, spriteDestinationX + spriteDestinationWidth, spriteDestinationY + spriteDestinationHeight);

        canvas.drawBitmap(spriteSheet, spriteSource, spriteDestination, null);
    }

    public void keyPressed(int key) {

    }

    public void keyReleased(int key) {

    }

    public boolean backPressed() {
        return true;
    }

    public void surfaceChanged(int width, int height) {

    }

    public void surfaceCreated() {

    }

    public void surfaceDestroyed() {

    }

    /**
     * Parmak ekrana dokunduğunda çalışır.
     * @param x Dokunulan noktanın x eksenindeki koordinatını tutar.
     * @param y Dokunulan noktanın y eksenindeki koordinatını tutar.
     * @param id Birden fazla parmak ekrana dokunduğunda parmaklara id verir.
     */
    public void touchDown(int x, int y, int id) {
        touchDownX = x;
        touchDownY = y;

        if(x > buttonDestinationX && y > buttonDestinationY && x < (buttonDestinationX + buttonDestinationWidth) && y < (buttonDestinationY + buttonDestinationHeight)) {
            //Log.i(TAG, "Button Clicked!");
            buttonSourceX = 1190;
            animationType = 3;
            spriteIndicatorX = 0;
            spriteIndicatorY = 0;
            frameNum = 11;
            shotControl = 1;
            bulletDestinationX = spriteDestinationX;
            bulletDestinationY = spriteDestinationY;
            bulletState = true;
        }
    }

    public void touchMove(int x, int y, int id) {

    }

    /**
     * Parmak ekrandan kalktığında çalışır.
     * @param x Dokunulan noktanın x eksenindeki koordinatını tutar.
     * @param y Dokunulan noktanın y eksenindeki koordinatını tutar.
     * @param id Birden fazla parmak ekrana dokunduğunda parmaklara id verir.
     */
    public void touchUp(int x, int y, int id) {

        int diffrenceX = x - touchDownX;
        int diffrenceY = y - touchDownY;


        if(!(x > buttonDestinationX && y > buttonDestinationY && x < (buttonDestinationX + buttonDestinationWidth) && y < (buttonDestinationY + buttonDestinationHeight))) {
            //Log.i(TAG, "Button Clicked!");
            // butona tıklanmadığında burası çalışır.

            // Parmağın ekrana dokunup ekrandan ayrıldığı sürede x ekseninde fark büyükse x eksenin y ekseninde fark büyükse y ekseninde hareket etsin.
            if (Math.abs(diffrenceX) >= Math.abs(diffrenceY)) {

                bulletDirectionY = 0;
                bulletDirectionX = 1;

                spriteIndicatorX = 1;
                animationType = 1;
                direction = 3;

                if (diffrenceX < 0) {
                    bulletDirectionX = - 1;
                    spriteIndicatorX = -1;
                    direction = 7;
                }

                spriteIndicatorY = 0;
            } else {

                bulletDirectionX = 0;
                bulletDirectionY = 1;

                spriteIndicatorY = 1;
                animationType = 1;
                direction = 9;

                if (diffrenceY < 0) {

                    bulletDirectionY = -1;
                    spriteIndicatorY = -1;
                    direction = 5;
                }

                spriteIndicatorX = 0;
            }
        } else {
            // butona dokunulduğunda burası çalışır.
            buttonSourceX = 0;
        }

    }



    public void pause() {

    }


    public void resume() {

    }


    public void reloadTextures() {

    }


    public void showNotify() {
    }

    public void hideNotify() {
    }

    /* Utilities */

    /**
     * İki rect nesnesinin çarpışıp çarpışmadığını kontrol eder.
     * @param rect1 Çarpışma kontrolüne girecek 1. rect nesnesi.
     * @param rect2 Çarpışma kontrolüne girecek 2. rect nesnesi.
     * @return Nesneler çarpışıyorsa true çarpışmıyorsa false değeri döndürür.
     */
    private boolean checkCollide(Rect rect1, Rect rect2) {
        return Rect.intersects(rect1, rect2);
    }
}
