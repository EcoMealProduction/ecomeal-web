
import React from 'react';
import { Button } from '@/components/ui/button';
import { MapPin, Clock, Leaf } from 'lucide-react';
import { Link } from 'react-router-dom';

export const HeroSection = () => {
  return (
    <section className="relative min-h-screen flex items-center justify-center overflow-hidden bg-gradient-to-br from-green-50 via-white to-orange-50 py-20">
      {/* Background decorative elements */}
      <div className="absolute inset-0">
        <div className="absolute top-20 left-10 w-20 h-20 bg-green-200 rounded-full opacity-20 animate-pulse"></div>
        <div className="absolute top-40 right-20 w-16 h-16 bg-orange-200 rounded-full opacity-30 animate-pulse delay-300"></div>
        <div className="absolute bottom-20 left-20 w-24 h-24 bg-green-300 rounded-full opacity-15 animate-pulse delay-700"></div>
        <div className="absolute bottom-40 right-10 w-32 h-32 bg-orange-300 rounded-full opacity-10 animate-pulse delay-1000"></div>
      </div>

      <div className="relative z-10 max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 pt-20">
        <div className="grid lg:grid-cols-2 gap-12 items-center">
          {/* Left Content */}
          <div className="text-center lg:text-left">
            <div className="mb-6">
              <span className="inline-flex items-center px-4 py-2 rounded-full text-sm font-medium bg-green-100 text-green-800 mb-4">
                🌍 Save Food, Save Planet
              </span>
            </div>
            
            <h1 className="text-5xl lg:text-6xl font-bold text-gray-900 mb-6 leading-tight">
              Delicious Food,
              <span className="text-green-600 block">Half the Price</span>
            </h1>
            
            <p className="text-xl text-gray-600 mb-8 leading-relaxed">
              Join thousands fighting food waste while saving money. Discover amazing surplus food 
              from your favorite cafes, bakeries, and restaurants at up to 50% off.
            </p>

            {/* Stats
            <div className="flex flex-wrap gap-6 mb-8 justify-center lg:justify-start">
              <div className="flex items-center gap-2 text-gray-700">
                <Leaf className="text-green-600" size={20} />
                <span className="font-semibold">12K+ meals saved</span>
              </div>
              <div className="flex items-center gap-2 text-gray-700">
                <MapPin className="text-green-600" size={20} />
                <span className="font-semibold">200+ partners</span>
              </div>
              <div className="flex items-center gap-2 text-gray-700">
                <Clock className="text-green-600" size={20} />
                <span className="font-semibold">Available 24/7</span>
              </div>
            </div> */}

            {/* CTA Buttons */}
            <div className="flex flex-col sm:flex-row gap-4 justify-center lg:justify-start">
              {/* <Link to="/onboarding"> */}
                <Button size="lg" className="bg-green-600 hover:bg-green-700 text-lg px-8 py-4 h-auto">
                  Start Saving Food
                </Button>
              {/* </Link> */}
              <Button 
                size="lg" 
                variant="outline" 
                className="border-green-600 text-green-600 hover:bg-green-50 text-lg px-8 py-4 h-auto"
              >
                Watch How It Works
              </Button>
            </div>
          </div>

          {/* Right Content - Hero Image/Illustration */}
          <div className="relative">
            <div className="relative bg-white rounded-3xl shadow-2xl p-8 transform rotate-3 hover:rotate-0 transition-transform duration-500">
              <div className="bg-gradient-to-br from-green-400 to-green-600 rounded-2xl p-6 mb-6">
                <div className="text-white text-center">
                  <div className="text-4xl mb-2">🥐</div>
                  <h3 className="font-bold text-lg">Fresh Pastries</h3>
                  <p className="text-green-100">Bakery Dulce Vita</p>
                </div>
              </div>
              
              <div className="space-y-4">
                <div className="flex justify-between items-center">
                  <span className="text-gray-600">Original Price</span>
                  <span className="line-through text-gray-400">80 lei</span>
                </div>
                <div className="flex justify-between items-center">
                  <span className="font-semibold text-gray-900">Your Price</span>
                  <span className="text-2xl font-bold text-green-600">40 lei</span>
                </div>
                <div className="bg-orange-50 rounded-lg p-3 text-center">
                  <span className="text-orange-600 font-medium">⏰ 2 hours left</span>
                </div>
                <Button className="w-full bg-green-600 hover:bg-green-700">
                  Reserve Now
                </Button>
              </div>
            </div>

            {/* Floating elements */}
            <div className="absolute -top-6 -right-6 bg-orange-500 text-white rounded-full p-4 animate-bounce">
              <span className="text-2xl">50% OFF</span>
            </div>
            <div className="absolute -bottom-4 -left-4 bg-green-500 text-white rounded-full p-3 animate-pulse">
              <Leaf size={24} />
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};
