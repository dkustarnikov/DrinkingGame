package learning.udemy.drinkinggame.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import learning.udemy.drinkinggame.Model.Tile;
import learning.udemy.drinkinggame.R;

public class GameActivity extends AppCompatActivity {

    private CardView cardView;
    private Button nextButton;
    private Button backButton;
    private Button shortcutButton;
    private TextView numberOfTile;
    private TextView nameOfTile;

    private Button shortCutNextButton;
    private Button shortCutBackButton;

    private Button rollDiceButton;
    private TextView diceRollResult;

    private Button moveXnext;
    private Button automaticAction;

    private List<Tile> tileList = new ArrayList<>();
    private List<Tile> shortCutList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        cardView = findViewById(R.id.TileContainerCardView);
        nextButton = findViewById(R.id.goNextNormalButton);
        backButton = findViewById(R.id.backNormalButton);
        shortcutButton = findViewById(R.id.shortcutButton);
        numberOfTile = findViewById(R.id.numberTextViewTile);
        nameOfTile = findViewById(R.id.nameTextViewTile);

        shortCutBackButton = findViewById(R.id.backShortcutButton);
        shortCutNextButton = findViewById(R.id.goNextShortcutButton);

        rollDiceButton = findViewById(R.id.rollDiceButton);
        diceRollResult = findViewById(R.id.diceResultTextView);

        moveXnext = findViewById(R.id.moveXNextButton);
        automaticAction = findViewById(R.id.automaticActionButton);


        //Initially these buttons are gone (Not visible and not clickable)
        backButton.setVisibility(View.GONE);
        shortcutButton.setVisibility(View.GONE);
        shortCutBackButton.setVisibility(View.GONE);
        shortCutNextButton.setVisibility(View.GONE);

        final int[] tileNumber = {0};



        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tileNumber[0]++;
                numberOfTile.setText("#" + tileNumber[0]);
                nameOfTile.setText(tileList.get(tileNumber[0]).getNameOfAction());
                backButton.setVisibility(View.VISIBLE);

                if (tileNumber[0] == tileList.size() - 1) {
                    nextButton.setVisibility(View.GONE);
                }
                if (tileList.get(tileNumber[0]).isShortcutAvailable()) {
                    shortcutButton.setVisibility(View.VISIBLE);
                }
                else {
                    shortcutButton.setVisibility(View.GONE);
                }

                //Here I am going to set a random color for each tile
                setRandomColor(cardView);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tileNumber[0]--;
                numberOfTile.setText("#" + tileNumber[0]);
                nameOfTile.setText(tileList.get(tileNumber[0]).getNameOfAction());
                nextButton.setVisibility(View.VISIBLE);
                if (tileNumber[0] == 1) {
                    backButton.setVisibility(View.GONE);
                }
                if (tileList.get(tileNumber[0]).isShortcutAvailable()) {
                    shortcutButton.setVisibility(View.VISIBLE);
                }
                else {
                    shortcutButton.setVisibility(View.GONE);
                }
                setRandomColor(cardView);
            }
        });

        final int[] shortcutNumber = {0};

        shortcutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shortcutNumber[0] = 0;
                //The idea is that it changes the main route and adds 3 more stations
                backButton.setVisibility(View.GONE);
                nextButton.setVisibility(View.GONE);
                shortcutButton.setVisibility(View.GONE);
                shortCutBackButton.setVisibility(View.VISIBLE);
                shortCutNextButton.setVisibility(View.VISIBLE);

                moveXnext.setVisibility(View.GONE);

                numberOfTile.setText("#" + shortcutNumber[0]);
                nameOfTile.setText(shortCutList.get(shortcutNumber[0]).getNameOfAction());

                //shortcutNumber[0]++;

            }
        });

        shortCutBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shortcutNumber[0] == 0) {
                    backButton.setVisibility(View.VISIBLE);
                    nextButton.setVisibility(View.VISIBLE);
                    shortCutBackButton.setVisibility(View.GONE);
                    shortCutNextButton.setVisibility(View.GONE);

                    numberOfTile.setText("#" + tileList.get(tileNumber[0]));
                    nameOfTile.setText(tileList.get(tileNumber[0]).getNameOfAction());
                }
                else {
                    shortcutNumber[0]--;
                    numberOfTile.setText("#" + shortcutNumber[0]);
                    nameOfTile.setText(shortCutList.get(shortcutNumber[0]).getNameOfAction());
                }
            }
        });

        shortCutNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shortcutNumber[0] == shortCutList.size() - 1) {
                    backButton.setVisibility(View.VISIBLE);
                    nextButton.setVisibility(View.VISIBLE);
                    shortCutBackButton.setVisibility(View.GONE);
                    shortCutNextButton.setVisibility(View.GONE);
                    moveXnext.setVisibility(View.VISIBLE);
                    tileNumber[0] += 8;

                    numberOfTile.setText("#" + tileList.get(tileNumber[0]));
                    nameOfTile.setText(tileList.get(tileNumber[0]).getNameOfAction());
                }
                else {
                    shortcutNumber[0]++;
                    numberOfTile.setText("#" + shortcutNumber[0] + 1);
                    nameOfTile.setText(shortCutList.get(shortcutNumber[0]).getNameOfAction());
                }
            }
        });
        final Random rand = new Random();
        final int[] randomRolled = {0};
        rollDiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomRolled[0] = rand.nextInt(6) + 1;

                diceRollResult.setText(randomRolled[0] + " rolled");
                moveXnext.setText("Move " + randomRolled[0] + " forward");
            }
        });

        moveXnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tileNumber[0] + randomRolled[0] >= tileList.size()) {
                    Toast.makeText(GameActivity.this, "You are too far to go " + randomRolled[0] + " forward", Toast.LENGTH_LONG).show();
                }
                else {
                    tileNumber[0] += randomRolled[0];
                    numberOfTile.setText("#" + tileNumber[0]);
                    nameOfTile.setText(tileList.get(tileNumber[0]).getNameOfAction());
                    backButton.setVisibility(View.VISIBLE);

                    if (tileNumber[0] == tileList.size() - 1) {
                        nextButton.setVisibility(View.GONE);
                    }
                    if (tileList.get(tileNumber[0]).isShortcutAvailable()) {
                        shortcutButton.setVisibility(View.VISIBLE);
                    }
                    else {
                        shortcutButton.setVisibility(View.GONE);
                    }
                }
            }
        });

        automaticAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        //The list with all the actions is created
        //The first object is created in order to logically better connect #1 and index 1

       tileList.add(new Tile(""));
      tileList.add(new Tile("Everyone drink"));
      tileList.add(new Tile("Take a shot"));
      tileList.add(new Tile("Text or shot"));
      tileList.add(new Tile("Pick one person to shotgun a Whiteclaw"));
      tileList.add(new Tile("2 truths & 1 lie"));
      tileList.add(new Tile("Brunettes drink"));
      tileList.add(new Tile("Questions"));
      tileList.add(new Tile("Nose goes"));
      tileList.add(new Tile("Drink water"));
      tileList.add(new Tile("Chug a new drink and use a shortcut or just take a sip", true));
      tileList.add(new Tile("Blondes drink"));
      tileList.add(new Tile("Categories"));
      tileList.add(new Tile("Odds are"));
      tileList.add(new Tile("Waterfall"));
      tileList.add(new Tile("Everyone drink"));
      tileList.add(new Tile("Go back 4"));
      tileList.add(new Tile("Go forward 2"));
      tileList.add(new Tile("Spelling bee"));
      tileList.add(new Tile("Chug"));
      tileList.add(new Tile("Partner shot"));
      tileList.add(new Tile("Chug a new drink and use a shortcut or just take a sip", true));
      tileList.add(new Tile("Guess 5 animals (sharades)"));
      tileList.add(new Tile("Rhyming"));
      tileList.add(new Tile("Guess a number 1-10, if you can't you drink"));
      tileList.add(new Tile("Would you rather"));
      tileList.add(new Tile("Take a shot"));
      tileList.add(new Tile("Brown eyes drink"));
      tileList.add(new Tile("Back to start"));
      tileList.add(new Tile("Blue eyes drink"));
      tileList.add(new Tile("Text or shot"));
      tileList.add(new Tile("Facetime or shot"));
      tileList.add(new Tile("Chug a new drink and use a shortcut or just take a sip", true));
      tileList.add(new Tile("Make a rule"));
      tileList.add(new Tile("Drink what you roll"));
      tileList.add(new Tile("Flip a coin"));
      tileList.add(new Tile("Guess the song"));
      tileList.add(new Tile("Switch with someone"));
      tileList.add(new Tile("Back 4"));
      tileList.add(new Tile("Don't laugh (First person to laugh, drinks)"));
      tileList.add(new Tile("Chug"));
      tileList.add(new Tile("Everyone drinks"));


      //Shortcut cuts 4 tiles
      shortCutList.add(new Tile("Safe spot"));
      shortCutList.add(new Tile("Send a shot"));
      shortCutList.add(new Tile("Go back"));

    }

    void setRandomColor(CardView cardView) {
        //There are a total of 7 colors so far
        Random rand = new Random();
        switch (rand.nextInt(7) + 1) {
            case 1:
                //Primary
                cardView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case 2:
                //Primary dark
                cardView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
            case 3:
                //Accent
                cardView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                break;
            case 4:
                //green
                cardView.setBackgroundColor(getResources().getColor(R.color.greenColor));
                break;
            case 5:
                //orange
                cardView.setBackgroundColor(getResources().getColor(R.color.orangeColor));
                break;
            case 6:
                //yellow
                cardView.setBackgroundColor(getResources().getColor(R.color.yellowColor));
                break;
            case 7:
                //pink
                cardView.setBackgroundColor(getResources().getColor(R.color.pinkColor));
                break;

        }
    }

}
