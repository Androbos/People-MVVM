/**
 * Copyright 2016 Erik Jhordan Rey.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jhordan.people_mvvm;

import android.view.View;

import com.example.jhordan.people_mvvm.data.FakeRandomUserGeneratorAPI;
import com.example.jhordan.people_mvvm.model.People;
import com.example.jhordan.people_mvvm.viewmodel.PeopleDetailViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;

/**
 * Notes for Mac!!
 * <p/>
 * If you are on a Mac, you will probably need to configure the
 * default JUnit test runner configuration in order to work around a bug where IntelliJ / Android
 * Studio
 * does not set the working directory to the module being tested. This can be accomplished by
 * editing
 * the run configurations, Defaults -> JUnit and changing the working directory value to
 * $MODULE_DIR$
 * <p/>
 * You have to specify  sdk < 23 (Robolectric does not support API level 23.)
 * <p/>
 * https://github.com/robolectric/robolectric/issues/1648
 **/

@RunWith(RobolectricGradleTestRunner.class) @Config(constants = BuildConfig.class, sdk = 21)
public class PeopleDetailViewModelTest {

  private PeopleDetailViewModel mPeopleDetailViewModel;
  private People mPeople;

  @Before public void setUpDetailViewModelTest() {
    mPeople = FakeRandomUserGeneratorAPI.getPeople();
    mPeopleDetailViewModel = new PeopleDetailViewModel(mPeople);
  }

  @Test public void shouldGetURLPictureProfile() throws Exception {
    assertEquals(mPeople.mPicture.large, mPeopleDetailViewModel.getPictureProfile());
  }

  @Test public void shouldGetUserName() throws Exception {
    assertEquals(mPeople.mUserName.userName, mPeopleDetailViewModel.getUserName());
  }

  @Test public void shouldGetCell() throws Exception {
    assertEquals(mPeople.mCell, mPeopleDetailViewModel.getCell());
  }

  @Test public void shouldGetMail() throws Exception {
    assertEquals(mPeople.mMail, mPeopleDetailViewModel.getEmail());
  }

  @Test public void shouldGetGender() throws Exception {
    assertEquals(mPeople.mGender, mPeopleDetailViewModel.getGender());
  }

  @Test public void shouldGetAddress() throws Exception {
    String fakeAddress =
        mPeople.mLocation.mStreet + " " + mPeople.mLocation.mCity + " " + mPeople.mLocation.mState;
    assertEquals(fakeAddress, mPeopleDetailViewModel.getAddress());
  }

  @Test public void givenTheEmailVisibilityVisible() throws Exception {
    assertEquals(View.VISIBLE, mPeopleDetailViewModel.getEmailVisibility());
  }

  @Test public void givenTheEmailVisibilityGone() throws Exception {
    mPeople.mMail = null;
    assertEquals(View.GONE, mPeopleDetailViewModel.getEmailVisibility());
  }
}
