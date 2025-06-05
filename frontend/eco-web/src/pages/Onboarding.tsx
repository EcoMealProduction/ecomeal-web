
import React, { useState } from 'react';
import { ArrowRight, MapPin, Heart, Leaf } from 'lucide-react';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Card, CardContent } from '@/components/ui/card';
import { useNavigate } from 'react-router-dom';

const Onboarding = () => {
  const [step, setStep] = useState(1);
  const [city, setCity] = useState('Chisinau');
  const navigate = useNavigate();

  const handleNext = () => {
    if (step < 3) {
      setStep(step + 1);
    } else {
      navigate('/');
    }
  };

  const renderStep = () => {
    switch (step) {
      case 1:
        return (
          <div className="text-center space-y-6">
            <div className="text-6xl mb-4">🌱</div>
            <h1 className="text-3xl font-bold text-gray-900">Welcome to EcoMeal</h1>
            <p className="text-lg text-gray-600 leading-relaxed">
              Join the fight against food waste while discovering amazing food at great prices from local restaurants and cafes.
            </p>
            <div className="bg-green-50 p-4 rounded-lg">
              <div className="flex items-center justify-center gap-8 text-sm">
                <div className="text-center">
                  <div className="text-2xl mb-1">🍽️</div>
                  <span className="text-green-700">Save Meals</span>
                </div>
                <div className="text-center">
                  <div className="text-2xl mb-1">💰</div>
                  <span className="text-green-700">Save Money</span>
                </div>
                <div className="text-center">
                  <div className="text-2xl mb-1">🌍</div>
                  <span className="text-green-700">Save Planet</span>
                </div>
              </div>
            </div>
          </div>
        );
      
      case 2:
        return (
          <div className="text-center space-y-6">
            <MapPin className="w-16 h-16 text-green-600 mx-auto" />
            <h1 className="text-2xl font-bold text-gray-900">Choose Your City</h1>
            <p className="text-gray-600">
              We'll show you the best food offers available in your area.
            </p>
            <div className="space-y-3">
              <Input
                value={city}
                onChange={(e) => setCity(e.target.value)}
                placeholder="Enter your city"
                className="text-center text-lg"
              />
              <div className="text-sm text-gray-500">
                Popular: Chisinau, Balti, Tiraspol
              </div>
            </div>
          </div>
        );
      
      case 3:
        return (
          <div className="text-center space-y-6">
            <div className="relative">
              <Heart className="w-16 h-16 text-red-500 mx-auto fill-current" />
              <Leaf className="w-8 h-8 text-green-500 absolute -top-2 -right-2" />
            </div>
            <h1 className="text-2xl font-bold text-gray-900">You're All Set!</h1>
            <p className="text-gray-600">
              Start your sustainable food journey and make a positive impact on your community and the environment.
            </p>
            <Card className="bg-orange-50 border-orange-200">
              <CardContent className="p-4">
                <h3 className="font-medium text-orange-800 mb-2">🎉 Welcome Bonus</h3>
                <p className="text-sm text-orange-700">
                  Get 20% extra discount on your first 3 orders this week!
                </p>
              </CardContent>
            </Card>
          </div>
        );
      
      default:
        return null;
    }
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-green-50 to-orange-50 flex flex-col">
      <div className="flex-1 flex flex-col justify-center p-6 max-w-md mx-auto w-full">
        {/* Progress Indicator */}
        <div className="flex justify-center mb-8">
          <div className="flex space-x-2">
            {[1, 2, 3].map((i) => (
              <div
                key={i}
                className={`w-3 h-3 rounded-full transition-colors ${
                  i <= step ? 'bg-green-600' : 'bg-gray-300'
                }`}
              />
            ))}
          </div>
        </div>

        {/* Step Content */}
        <div className="flex-1 flex flex-col justify-center">
          {renderStep()}
        </div>

        {/* Navigation */}
        <div className="space-y-4 mt-8">
          <Button 
            onClick={handleNext}
            className="w-full bg-green-600 hover:bg-green-700 text-white py-3 text-lg"
          >
            {step === 3 ? 'Start Exploring' : 'Continue'}
            <ArrowRight className="ml-2" size={20} />
          </Button>
          
          {step > 1 && (
            <Button 
              variant="ghost" 
              onClick={() => setStep(step - 1)}
              className="w-full text-gray-600"
            >
              Back
            </Button>
          )}
        </div>
      </div>
    </div>
  );
};

export default Onboarding;
