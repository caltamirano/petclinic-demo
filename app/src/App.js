import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import PetList from './PetList';
import VetList from './VetList';
import AppointmentList from './AppointmentList';
import PetEdit from './PetEdit';
import PetAppointmentList from './PetAppointmentList';
import PetAppointmentEdit from './PetAppointmentEdit';
import VetEdit from './VetEdit';
import VetAppointmentList from './VetAppointmentList';

class App extends Component {
  render() {
    return (
      <Router>
        <Switch>
          <Route path='/' exact={true} component={Home}/>
          <Route path='/pets' exact={true} component={PetList}/>
          <Route path='/vets' exact={true} component={VetList}/>
          <Route path='/appointments' exact={true} component={AppointmentList}/>
          <Route path='/pets/:id' exact={true} component={PetEdit}/>
          <Route path='/pets/:id/appointments' exact={true} component={PetAppointmentList}/>
          <Route path='/pets/:petId/appointments/:appointmentId' exact={true} component={PetAppointmentEdit}/>
          <Route path='/vets/:id' exact={true} component={VetEdit}/>
          <Route path='/vets/:id/appointments' exact={true} component={VetAppointmentList}/>
        </Switch>
      </Router>
    )
  }
}

export default App;